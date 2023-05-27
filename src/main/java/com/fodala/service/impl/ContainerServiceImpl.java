package com.fodala.service.impl;

import com.fodala.mapper.ContainerMapper;
import com.fodala.pojo.Container;
import com.fodala.pojo.ContainerHistory;
import com.fodala.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerMapper containerMapper;

    @Override
    public List<Container> all() {
        return containerMapper.all();
    }

    @Override
    public Container findById(Integer id) {
        return containerMapper.findById(id);
    }

    @Override
    public void insert(Container container) {
        containerMapper.insert(container);
    }

    @Override
    public void update(Container container) {
        containerMapper.update(container);
    }

    @Override
    public void delete(Integer id) {
        containerMapper.delete(id);
    }

    @Override
    public List<ContainerHistory> history(Integer id) {
        return containerMapper.history(id);
    }

    @Override
    public Container createEmpty() {
        Random r = new Random();
        Container container = new Container();
        String name = "Container" + r.nextInt(10000);
        container.setName(name);
        return container;
    }

}