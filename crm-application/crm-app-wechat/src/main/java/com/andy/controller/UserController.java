package com.andy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.andy.api.UserFeignClient;
import com.andy.po.User;
import com.andy.service.UserService;
import com.andy.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "UserController",description = "用户管理")
@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @ApiOperation(value = "获取所有用户")
    @SentinelResource("getusers")
    @RequestMapping(value = "/getusers",method = RequestMethod.GET)
    public ResponseEntity<String> getUsers(){
        String s = userFeignClient.getUsers();
        return ResponseEntity.ok("查询成功",s).build();
    }
}
