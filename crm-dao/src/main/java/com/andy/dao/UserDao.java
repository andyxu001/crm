package com.andy.dao;

import com.andy.mapper.UserMapper;
import com.andy.po.User;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public List<User> getAllUsers(Integer pageNum, Integer pageSize) {
         PageHelper.startPage(pageNum,pageSize);
         return userMapper.getAll();
    }

    public User findUserInforById(Integer id){
        return userMapper.findUserInforById(id);
    }
}
