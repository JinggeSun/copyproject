package com.sun.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表示一个服务
 * 一个类下面的一个方法，就是一个服务。
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    /**
     * 类名
     */
    private String clazz;
    /**
     * 方法名
     */
    private String method;

    /**
     * 返回类型
     */
    private String returnType;

    /**
     * 返回的类型
     */
    private String[] parameterTypes;

    public static <T> ServiceDescriptor from(Class<T> clazz, Method method){
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        Class[] classes = method.getParameterTypes();
        String[] list = Arrays.stream(classes).map(Class::getName).toArray(String[]::new);
        serviceDescriptor.setParameterTypes(list);
        return serviceDescriptor;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != getClass()){
            return false;
        }
        ServiceDescriptor serviceDescriptor = (ServiceDescriptor) obj;
        return serviceDescriptor.toString().equals(this.toString());
    }
}
