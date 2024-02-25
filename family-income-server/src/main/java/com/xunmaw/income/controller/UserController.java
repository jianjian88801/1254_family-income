package com.xunmaw.income.controller;

import com.xunmaw.income.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.income.entity.ApiR;
import com.xunmaw.income.entity.Users;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final static String SUCCESS_MSG = "接口请求成功";
    private final static String ERROR_MSG = "接口请求失败";
    private final static Integer SUCCESS_CODE = 200;
    private final static Integer ERROR_CODE = 400;

    @Autowired
	Userservice userservice;

    /**
     * 根据 pkey 找到家庭成员
     * @param pkey
     * @return
     */
    @ResponseBody
    @GetMapping("/getFamilyMs")
    public ApiR getFamilyMs(String pkey){
        List<Users> users = userservice.getUsers(pkey);
        List<String> names = new ArrayList<>();
        for (Users user : users) {
            names.add(user.getName());
        }
        List<Object> rst = new ArrayList<>();
        rst.add(users);
        rst.add(names);
        return new ApiR(SUCCESS_CODE, SUCCESS_MSG, rst);
    }

    /**
     * 用户添加
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    @ResponseBody
    public ApiR addUser(Users user) {
        return userservice.addUser(user) == 1 ? new ApiR(SUCCESS_CODE, SUCCESS_MSG, 1) : new ApiR(ERROR_CODE, ERROR_MSG, 0);
    }

    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    @PutMapping("/user")
    @ResponseBody
    public ApiR updateUser(Users user) {
        return userservice.updateUser(user) >= 1 ? new ApiR(SUCCESS_CODE, SUCCESS_MSG, 1) : new ApiR(ERROR_CODE, ERROR_MSG, 0);
    }

    /**
     * 用户信息读取
     *
     * @param id
     * @return
     */
    @GetMapping("/user")
    @ResponseBody
    public ApiR getUser(Integer id) {
        return new ApiR(SUCCESS_CODE, SUCCESS_MSG, userservice.findUserById(id));
    }

    /**
     * 用户登录
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public ApiR userLogin(String username, String password) {
        Users user = new Users();
        user.setSjh(username);
        user.setPwd(password);
        return userservice.userLogin(user);
    }
    @GetMapping("/loginPage")
    public void loginPage(){

    }

}
