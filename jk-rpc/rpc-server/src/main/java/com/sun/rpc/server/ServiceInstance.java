package com.sun.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 一个服务实例
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;

}
