package com.fodala.service;

import com.fodala.pojo.User;
import com.fodala.pojo.UserHistory;
import com.fodala.pojo.UserImage;

import java.util.List;

public interface UserService {

    List<User> all();

    User findById(Integer id);

    void insert(User user);

    void update(User user);

    void delete(Integer id);

    List<UserHistory> history(Integer id);

    UserImage selectImage(Integer id);

    User createEmpty();
}
