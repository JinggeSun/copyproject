package com.sun.rpc.common.utils;

import com.sun.jdi.connect.spi.TransportService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具类
 * @author zcm
 */
public class ReflectionsUtils {

    /**
     * 根据类，创建类的对象。
     * 万事万物皆对象。反射知识
     * @param clazz 类
     * @param <T>
     * @return 对象。返回的对象
     * @throws IllegalAccessException
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            T t =  clazz.newInstance();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回类下面所有的公共方法（就是用户自定义的方法）
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        //获取类下面的所以方法
        Method[] methods = clazz.getDeclaredMethods();
        //遍历公共方法
        //使用过滤的方式，满足条件的留下
        //返回数组
        return Arrays.stream(methods).filter(method -> {
            return Modifier.isPublic(method.getModifiers());
        }).toArray(Method[]::new);
    }

    /**
     * 反射基本知识
     * 调用制定对象的制定方法
     * @param object 被调用的对象
     * @param method 被调用的方法
     * @param objects 方法的参数
     * @return
     */
    public static Object inovke(Object object,Method method,Object... objects){
        try {
            return method.invoke(object,objects);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        TestInter test = newInstance(TestInter.class);
        assert test != null;
    }
}
