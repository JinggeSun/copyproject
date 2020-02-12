package com.sun.order.exception;


import com.sun.order.enums.ResultEnum;

/**
 * @author zcm
 */
public class OrderException extends RuntimeException{

    private Integer code;

    public OrderException (Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException (ResultEnum resultEnum){
        super((resultEnum.getMessage()));
        this.code = resultEnum.getCode();
    }


}
