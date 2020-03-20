package com.sun.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zcm
 */
@Controller
public class PageController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
