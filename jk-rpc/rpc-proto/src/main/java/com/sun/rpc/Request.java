package com.sun.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc请求
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    /**
     * 一个服务
     */
    private ServiceDescriptor serviceDescriptor;
    /**
     * 携带的参数
     */
    private Object[] parameters;
}
