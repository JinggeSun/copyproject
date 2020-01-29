package com.sun.jwt.config;

import com.sun.jwt.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置filter
 * @author zcm
 */
@Configuration
public class MyConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistration(){
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        //注册filter
        JwtFilter jwtFilter = new JwtFilter();
        registration.setFilter(jwtFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/home/*");
        registration.setUrlPatterns(urlPatterns);
        return registration;
    }
}
