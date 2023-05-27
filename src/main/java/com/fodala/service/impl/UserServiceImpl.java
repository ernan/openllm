package com.fodala.service.impl;

import com.fodala.mapper.UserMapper;
import com.fodala.pojo.User;
import com.fodala.pojo.UserHistory;
import com.fodala.pojo.UserImage;
import com.fodala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> all() {
        return userMapper.all();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public List<UserHistory> history(Integer id) {
        return userMapper.history(id);
    }

    @Override
    public UserImage selectImage(Integer id) {
        return userMapper.selectImage(id);
    }

    @Override
    public User createEmpty() {
        Random r = new Random();
        User user = new User();
        String name = "A Name" + r.nextInt(10000);
        user.setName(name);
        user.setUsername(name);
        user.setEmail(name + "@email.com");
        user.setPassword(name + "Password");
        return user;
    }

}