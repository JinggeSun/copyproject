package com.sun.my;

import com.sun.mini.starter.MiniApplication;

/**
 * @author zcm
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("hello word!");
        MiniApplication.run(Application.class,args);
    }
}
