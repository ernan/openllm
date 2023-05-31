package com.fodala.service.impl;

import com.fodala.service.UserImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserImageServiceImpl implements UserImageService {
    private static final Logger logger = LoggerFactory.getLogger(UserImageServiceImpl.class);
    private @Autowired
    DataSource ds;

    @Override
    public Integer saveImage(String tableName, Integer userId, byte[] data) {
        String updateSQL = "insert into " + tableName + "(photo, user_id) values (?, ?)";
        int result = -1;
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setBytes(1, data);
            ps.setInt(2, userId);
            result = ps.executeUpdate();
            logger.info("Stored the file in the BLOB column.");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Integer updateImage(byte[] data, String tableName, Integer id) {
        String updateSQL = "UPDATE " + tableName + "SET photo = ? WHERE id= ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setBytes(1, data);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Stored the file in the BLOB column.");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return -1;
        }
        return 0;
    }

    @Override
    public byte[] readImage(Integer id, String tableName) {
        String selectSQL = "SELECT photo FROM " + tableName + " WHERE id=";
        FileOutputStream fos = null;
        String prefix = "image";
        String suffix = "png";
        String directoryPath = System.getProperty("user.dir");
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // write binary stream into file
            File file = File.createTempFile(prefix, suffix, new File(directoryPath));
            fos = new FileOutputStream(file);

            logger.info("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("picture");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
            }
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
