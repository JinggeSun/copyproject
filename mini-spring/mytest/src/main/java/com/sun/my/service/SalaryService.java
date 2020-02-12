package com.sun.my.service;

import com.sun.mini.beans.Bean;

/**
 * @author zcm
 */
@Bean
public class SalaryService {

    public int calSalay(Integer exp){
        return exp * 1000;
    }
}
