package com.sun.nss.common;

import com.sun.nss.common.exception.enums.ErrorEnum;

import java.util.HashMap;

/**
 * http返回类
 * 继承，可以流编程,重写put
 */
public class Result extends HashMap<String,Object> {

    /**
     * 默认构造函数，成功
     * success
     */
    public Result(){
        put("code",200);
        put("msg","success");
    }

    public static Result ok() {
        return new Result();
    }

    public static Result error() {
        return error(ErrorEnum.UNKNOWN);
    }

    public static Result error(ErrorEnum eEnum) {
        return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
    }

    public static Result error(String msg) {
        return new Result().put("msg",msg).put("code", ErrorEnum.UNKNOWN.getCode());
    }

    public static Result error(Integer code , String msg){
        return new Result().put("code",code).put("msg",msg);
    }

    public static Result exception() {
        return exception(ErrorEnum.UNKNOWN);
    }

    public static Result exception(ErrorEnum eEnum) {
        return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
    }


    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
