package com.sun.learn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zcm
 */
@Getter
@AllArgsConstructor
public enum  ExceptionEnums {

    LOGIN_SUCCESS(200,"SUCCESS"),
    LOGIN_FAILURE(400,"FAILURE"),
    LOGIN_NO_ACCOUNT(400,"用户名不存在"),
    LOGIN_PASSWORD_ERROR(400,"密码错误"),
    LOGIN_ACCOUNT_LOCK(400,"账户停用"),
    LOGIN_CAPTCHA(400,"验证码错误"),
    ;

    private Integer code;
    private String message;
}
