package com.sun.nine.lambda.cart;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  SkuCategory {

    CLOTHING(10,"服装类"),
    ELECTRONICS(20,"数码类"),
    SPORTS(30,"运动类"),
    BOOKS(40,"图书类");


    private Integer code;
    private String name;


}
