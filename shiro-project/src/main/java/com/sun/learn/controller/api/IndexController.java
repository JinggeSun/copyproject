package com.sun.learn.controller.api;

import com.alibaba.fastjson.JSON;
import com.sun.learn.controller.BaseController;
import com.sun.learn.entity.Account;
import com.sun.learn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页控制器
 * @author zcm
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    AccountService accountService;

    @GetMapping("/menu")
    public String getMenu(){
        Map<String,Object> map = accountService.findMenuById(1);
        return JSON.toJSONString(map);
    }

}
