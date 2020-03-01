package com.sun.task.task;

import com.sun.task.util.SpringContextUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author zcm
 */
@Slf4j
public class SchedulingRunnable implements Runnable{

    /**
     * bean名字 class
     */
    private String beanName;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    public SchedulingRunnable(String beanName,String methodName){
        this(beanName,methodName,null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {

        log.info("定时任务开始执行--bean:{},方法:{},参数:{}",beanName,methodName,params);

        long startTime = System.currentTimeMillis();

        try {
            //获取bean对象
            Object target = SpringContextUtils.getBan(beanName);
            Method method = null;
            if (!StringUtils.isEmpty(params)){
                method = target.getClass().getDeclaredMethod(methodName,String.class);
            }else {
                method = target.getClass().getDeclaredMethod(methodName);
            }
            if (!StringUtils.isEmpty(params)){
                method.invoke(target,params);
            }else {
                method.invoke(target);
            }

        }catch (Exception e){
            log.error(String.format("定时任务异常-bean:%s,方法：%s,参数：%s",beanName,methodName,params));
        }

        long times = System.currentTimeMillis() - startTime;

        log.info("定时任务执行完毕 -bean:{},方法:{},参数:{}",beanName,methodName,params);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }

}
