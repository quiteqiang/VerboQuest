package com.example.VerboQuest.controller;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.dto.UserDto;
import com.example.VerboQuest.service.IUserService;
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
    public ResponseMessage get(@PathVariable Integer userId) {
        System.out.println("get userId " + userId);
        User userNew = userService.getUser(userId);
        return ResponseMessage.success(userNew);
    }
    //修改
    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody UserDto user) {
        User userNew = userService.edit(user);
        return ResponseMessage.success(userNew);
    }

    //删除
    @DeleteMapping("/{userId}")        // localhost:8088/user/1
    public ResponseMessage delete(@PathVariable Integer userId) {
        System.out.println("request userId.  " + userId);
        userService.delete(userId);
        return ResponseMessage.success();
    }
}
