package com.example.demo.controller;

import com.example.demo.pojo.ResponseMessage;
import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //接口方法返回对象 转换为json text
@RequestMapping("/user")  // localhost:8088/user/**
public class UserController {
    //增加
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseMessage<User> add(@Validated @RequestBody UserDto user) {
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }

    //查询
    @GetMapping("/{userId}")        // localhost:8088/user/1
    public ResponseMessage add (@PathVariable Integer userId) {
        User userNew = userService.getUser(userId);
        return ResponseMessage.success(userNew);
    }
    //修改
    //删除
}
