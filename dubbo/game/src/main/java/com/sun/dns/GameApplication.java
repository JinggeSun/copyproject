package com.sun.dns;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class GameApplication {



	public static void main(String[] args) {
		
		SpringApplication.run(GameApplication.class, args);
	}
	

}
