package com.sun.nacoslearn;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcm
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example",autoRefreshed = true)
public class NacoslearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacoslearnApplication.class, args);
	}

}
