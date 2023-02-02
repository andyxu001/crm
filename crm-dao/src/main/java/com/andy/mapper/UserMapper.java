package com.andy.mapper;

import com.andy.po.User;
import java.util.List;

public interface UserMapper {

    public List<User> getAll();
    public User findUserInforById(Integer id);
}
