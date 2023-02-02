package com.andy.service;

import com.andy.po.User;
import java.util.List;

public interface UserService {

      public List<User> getAll();

      public User findUserInforById(Integer id);
}
