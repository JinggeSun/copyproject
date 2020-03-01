package com.sun.task.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 * @author zcm
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取bean
     * @param name
     * @return
     */
    public static Object getBan(String name){
        return applicationContext.getBean(name);
    }
    /**
     * 获取bean
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }

    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name){
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name){
        return applicationContext.getType(name);
    }

}
