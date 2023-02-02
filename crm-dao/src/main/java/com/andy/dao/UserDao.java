package com.andy.dao;

import com.andy.mapper.UserMapper;
import com.andy.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
       return userMapper.getAll();
    }

    public User findUserInforById(Integer id){
        return userMapper.findUserInforById(id);
    }
}
