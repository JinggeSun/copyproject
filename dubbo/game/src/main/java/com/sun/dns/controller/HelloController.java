package com.sun.dns.controller;

import com.sun.dns.service.DnsService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
public class HelloController {

    @Reference(loadbalance = "roundrobin")
    private DnsService demoService;

    @GetMapping("/")
    public String ged() {
        return "ddddd";
    }

    @GetMapping("/index")
    public String init() {
        String sayHello = demoService.sayHello("world");
        System.err.println(sayHello);
        return sayHello;
    }
}
