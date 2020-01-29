package com.sun.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
public class HomePageController {


    @GetMapping("/hello/welcome")
    public String homePage(){

        return "welcome to home page.";
    }
}
