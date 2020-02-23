package com.sun.rpc.server;

import com.sun.rpc.Request;
import com.sun.rpc.ServiceDescriptor;
import com.sun.rpc.common.utils.ReflectionsUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理类
 * 1。 注册服务
 * 2， 管理服务
 * @author zcm
 */
public class ServiceManager {

    private Map<ServiceDescriptor,ServiceInstance> services ;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass,T bean){
        Method[] methods = ReflectionsUtils.getPublicMethods(interfaceClass);

        Arrays.stream(methods).forEach(method -> {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setTarget(bean);
            serviceInstance.setMethod(method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass,method);
            services.put(serviceDescriptor,serviceInstance);
        });
    }

    /**
     * 获取
     * @param request
     * @return
     */
    public ServiceInstance lookup(Request request){
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return services.get(serviceDescriptor);
    }

}
