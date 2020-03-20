package com.sun.dns.controller;

import com.sun.dns.service.DnsService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
public class HellController {


    @GetMapping("/he")
    public String ged() {
        return "ddddd";
    }

}
