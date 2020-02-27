package com.sun.learn;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author zcm
 */
@SpringBootApplication
@MapperScan("com.sun.learn.mapper")
public class ShiroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroProjectApplication.class, args);
	}

}
