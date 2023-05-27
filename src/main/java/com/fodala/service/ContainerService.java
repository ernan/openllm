package com.fodala.service;

import com.fodala.pojo.Container;
import com.fodala.pojo.ContainerHistory;

import java.util.List;

public interface ContainerService {

    List<Container> all();

    Container findById(Integer id);

    void insert(Container Container);

    void update(Container Container);

    void delete(Integer id);

    List<ContainerHistory> history(Integer id);

    Container createEmpty();
}
