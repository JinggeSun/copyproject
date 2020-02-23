package com.sun.nss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zcm
 */
@SpringBootApplication
public class NssApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NssApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
