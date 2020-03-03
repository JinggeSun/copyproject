package com.sun.learn.util;

import com.sun.learn.enums.ExceptionEnums;
import com.sun.learn.vo.ResultVo;

/**
 * @author zcm
 */
public class ResultVoUtil {

    public static ResultVo successResult(){
        return new ResultVo(ExceptionEnums.LOGIN_SUCCESS,null);
    }

    public static ResultVo successResult(Object object){
        return new ResultVo(ExceptionEnums.LOGIN_SUCCESS,object);
    }
}
