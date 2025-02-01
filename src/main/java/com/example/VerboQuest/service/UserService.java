package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service        //Spring 的bean
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow( () -> {
            throw new IllegalArgumentException("Customer doesn't exist, invalid parameter");
        });
    }

    @Override
    public User add(UserDto user) {

        User userPojo = new User();

        BeanUtils.copyProperties(user, userPojo);

        //调用数据访问类的方法
        return userRepository.save(userPojo);
    }

    @Override
    public User edit(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        return userRepository.save(userPojo);
    }
}
