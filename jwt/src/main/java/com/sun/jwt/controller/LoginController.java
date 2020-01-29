package com.sun.jwt.controller;

import com.sun.jwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zcm
 */
@RestController
public class LoginController {

    private  String JWT_USER = "JWT_USER";

    private  String JWT_PASSWORD = "12345678";

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
        if (JWT_USER.equals(username) && JWT_PASSWORD.equals(password)){
            return jwtUtil.createJwtToken();
        }
        return "NOK";
    }

    @RequestMapping(value = "/home/welcome",method = RequestMethod.GET)
    @ResponseBody
    public String homePage(){
        return "welcome to home page";
    }

}
