package com.sun.nss.common.exception;

import com.sun.nss.common.Result;
import com.sun.nss.common.exception.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理类
 * @author zcm
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e){
        return new Result().put("code",e.getMsg()).put("msg",e.getMsg());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.PATH_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.DUPLICATE_KEY);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return Result.exception(ErrorEnum.NO_AUTH);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.exception();
    }
}
