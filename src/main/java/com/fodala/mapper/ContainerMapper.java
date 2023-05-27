package com.fodala.mapper;

import com.fodala.pojo.Container;
import com.fodala.pojo.ContainerHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerMapper {

    List<Container> all();

    Container findById(@Param("id") Integer id);

    void insert(@Param("container") Container user);

    void update(@Param("container") Container user);

    void delete(@Param("id") Integer id);

    List<ContainerHistory> history(@Param("id") Integer id);
}
