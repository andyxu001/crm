package com.andy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.andy.dao.UserDao;
import com.andy.po.User;
import com.andy.service.UserService;
import com.andy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findUserInforById(Integer id) {
        Map<Object, Object> map = RedisUtils.hget("user::" + id);
        if(!Objects.isNull(map) && map.size() > 0) {
            System.out.println("执行redis");
            User user = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
            return user;
        }
        System.out.println("执行数据库");
        User userInforById = userDao.findUserInforById(id);
        String userStr = JSON.toJSONString(userInforById);
        RedisUtils.hset("user::"+id,JSONObject.parseObject(userStr, Map.class));
        return userInforById;
    }
}
