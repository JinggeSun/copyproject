package com.sun.task.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zcm
 */
@Controller
public class PageController {

    @GetMapping("/login")
    private String login(){
        return "login";
    }

    @GetMapping("/index")
    private String index(){
        return "index";
    }

    @GetMapping("/table")
    private String table(){
        return "page/table";
    }
}
