package com.sun.rpc.server;

import com.sun.deploy.util.ReflectionUtil;
import com.sun.rpc.Request;
import com.sun.rpc.common.utils.ReflectionsUtils;

/**
 * @author zcm
 */
public class ServiceInvoker {

    public Object invoker(ServiceInstance serviceInstance, Request request){
        return ReflectionsUtils.inovke(serviceInstance.getTarget(),serviceInstance.getMethod(),request.getParameters());
    }
}
