package com.sun.rpc;

import lombok.Data;

/**
 * rpc相应
 * 跟web返回差不多。
 * 设置默认值
 * @author zcm
 */
@Data
public class Response {

    /**
     * 返回码 1 成功，非0 失败
     */
    private int code = 0;

    /**
     * 返回消息
     */
    private String message = "ok";

    /**
     * 返回数据
     */
    private Object data;
}
