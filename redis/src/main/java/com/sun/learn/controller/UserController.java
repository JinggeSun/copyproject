package com.sun.learn.controller;


import com.sun.learn.entity.User;
import com.sun.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/list")
    public User list(){
        return userService.list();
    }
}
