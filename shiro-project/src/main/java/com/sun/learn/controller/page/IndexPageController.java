package com.sun.learn.controller.page;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面配置
 * @author zcm
 */
@Controller
@RequestMapping("/page")
public class IndexPageController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * tab 的首页
     * @return
     */
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/account")
    public String user(){
        return "account";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noauth")
    public String noauth(){
        return "noauth";
    }


    @RequestMapping("/sys/rule")
    public String rule(){
        return "sys/rule";
    }
}
