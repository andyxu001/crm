package com.andy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.andy.po.User;
import com.andy.service.UserService;
import com.andy.util.ResponseEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

@Api(tags = "UserController",description = "用户管理")
@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    @ApiOperation(value = "获取所有用户")
    @RequestMapping(value = "/getusers",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userService.getAll();
        return ResponseEntity.ok("查询用户列表成功",userList).build();
    }

    @ApiOperation(value = "获取所有用户")
    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public ResponseEntity<PageInfo<User>> getAllUsers(Integer pageNum,Integer pageSize){
        return ResponseEntity.ok("分页查询用户成功",userService.getAllUser(pageNum,pageSize)).build();
    }

    @ApiOperation("根据用户ID获取用户信息")
    @RequestMapping(value = "/findUserInforById/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> findUserInforById(@PathVariable("id") Integer id){

        User userInforById = userService.findUserInforById(id);
        return ResponseEntity.ok("查询用户成功",userInforById).build();
    }

    @ApiOperation("发送消息")
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public ResponseEntity<Void> sendMsg() {
       for(int i = 1;i<1000;i++){
           User user = new User();
           user.setId(i);
           user.setName("赵六--"+i);
           user.setAge(i);
           user.setBirth(new Date());
           ListenableFuture<SendResult<Object, Object>> result = kafkaTemplate.send("test", 0,"test", JSONObject.toJSONString(user));
       }
       return ResponseEntity.ok("发送成功").build();
    }

    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<Void> upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        File dest = new File("e:/测试/"+file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(inputStream,dest);
        return ResponseEntity.ok("上传成功!").build();
    }
}
