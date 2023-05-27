package com.fodala.mapper;

import com.fodala.pojo.Role;
import com.fodala.pojo.User;
import com.fodala.pojo.UserHistory;
import com.fodala.pojo.UserImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> all();

    User findById(@Param("id") Integer id);

    User selectUserByName(@Param("username") String username);

    User findByEmail(@Param("email") String email);

    User findByUsername(@Param("username") String username);

    void insert(@Param("user") User user);

    void update(@Param("user") User user);

    void delete(@Param("id") Integer id);

    List<UserHistory> history(@Param("id") Integer id);

    List<Role> roles(@Param("username") String username);

    void insertImage(@Param("userImage") UserImage userImage);

    UserImage selectImage(@Param("id") Integer id);

    User selectUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
