package com.sun.nss.common.exception;

import com.sun.nss.common.exception.enums.ErrorEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class MyException extends RuntimeException{

    private String msg;
    private int code;

    public MyException(int code,String msg){
        super(msg);
        this.code = code;
    }

    public MyException(ErrorEnum errorEnum){
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

}
