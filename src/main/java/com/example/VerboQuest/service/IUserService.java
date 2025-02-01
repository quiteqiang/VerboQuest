package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.UserDto;

public interface IUserService {
    /**
     * Add user
     * @param user
     * */
    User add(UserDto user);

    User getUser(Integer userId);

    /**
     *
     * */
    User edit(UserDto user);
}
