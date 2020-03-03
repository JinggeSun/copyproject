package com.sun.learn.exception;

import com.sun.learn.enums.ExceptionEnums;

/**
 *
 * @author zcm
 */
public class CustomException extends RuntimeException{

    private Integer code;

    public CustomException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public CustomException(ExceptionEnums exceptionEnums){
        super(exceptionEnums.getMessage());
        this.code = exceptionEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
