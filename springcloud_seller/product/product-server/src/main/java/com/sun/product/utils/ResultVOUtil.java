package com.sun.product.utils;

import com.sun.product.VO.ResultVO;

/**
 * @author zcm
 */
public class ResultVOUtil {

    public static ResultVO <Object> success(Object object){
        ResultVO<Object> resultVO = new ResultVO<Object>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }
}
