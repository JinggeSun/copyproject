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

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

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

    @PostMapping("/login")
    public String login(String name, String password, Model model){
        /**
         * 使用shiro进行认证
         */
        //1. 获取subject主体
        Subject subject = SecurityUtils.getSubject();
        //2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3. 执行登陆
        try {
            subject.login(token);
            //登陆成功
            return "redirect:/page/index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
