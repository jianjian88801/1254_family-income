package com.xunmaw.income.service;

import com.xunmaw.income.entity.ApiR;
import com.xunmaw.income.entity.Users;

import java.util.List;

public interface Userservice {
    Integer addUser(Users user);

    Users findUserById(Integer id);

    Integer updateUser(Users user);

    ApiR userLogin(Users user);

    Users getUser(String username);
    List<Users> getUsers(String pkey);
}
