package com.sun.dns.service.impl;

import java.util.Date;

import com.sun.dns.service.DnsService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class DnsServiceImpl implements DnsService {

	@Override
    public String sayHello(String name) {
        return "Hello, " + name + ", " + new Date();
    }

}