package com.sun.learn.exception;

import com.sun.learn.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制器全局异常处理器
 * @author zcm
 */
@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultVo customException(CustomException e){
        System.out.println("进入异常。。。");
        return ResultVo.builder().code(e.getCode()).message(e.getMessage()).build();
    }
}
