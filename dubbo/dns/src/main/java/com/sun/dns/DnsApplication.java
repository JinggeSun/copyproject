package com.sun.dns;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcm
 */
@SpringBootApplication
@EnableDubbo
public class DnsApplication {


	public static void main(String[] args) {
		SpringApplication.run(DnsApplication.class, args);
	}
	
}
