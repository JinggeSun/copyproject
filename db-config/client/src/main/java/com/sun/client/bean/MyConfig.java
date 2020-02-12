package com.sun.client.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
@RefreshScope
public class MyConfig {

    @Value("${foo}")
    String foo;

    @GetMapping(value = "/foo")
    public String hi(){
        return foo;
    }
}
