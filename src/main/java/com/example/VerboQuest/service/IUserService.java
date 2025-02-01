package com.example.VerboQuest.service;

import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.dto.UserDto;

public interface IUserService {
    /**
     * Add user
     * @param user
     * */
    User add(UserDto user);

    User getUser(Integer userId);

    /**
     * Edit user
     * @param user
     * */
    User edit(UserDto user);

    /**
     * Delete user
     *
     * @param userId
     */
    void delete(Integer userId);
}
