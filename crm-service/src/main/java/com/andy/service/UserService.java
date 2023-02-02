package com.andy.service;

import com.andy.po.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

      public List<User> getAll();

      public PageInfo<User> getAllUser(int pageNum,int pageSize);

      public User findUserInforById(Integer id);
}
