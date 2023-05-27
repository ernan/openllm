package com.fodala.service.impl;

import com.fodala.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Service
public class DataLoaderImpl implements DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderImpl.class);

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }


    @Override
    public void loadData(String resourceFile) {
        DataSource ds = dataSource();
        try (Connection connection = ds.getConnection()) {
            String sql = loadResource(resourceFile);
            Statement statement = connection.createStatement();
            logger.info("Executing \n{}\n", sql);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            logger.info("Completed loading {}.", resourceFile);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    String loadResource(String name) {
        logger.info("Loading resource {}", name);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:" + name);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}
