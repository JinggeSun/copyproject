package com.sun.mini.web.handler;

import com.sun.mini.web.mvc.Controller;
import com.sun.mini.web.mvc.RequestMapping;
import com.sun.mini.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */
public class HandlerManager {

    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static void resolveMappingHandler(List<Class<?>> clsList){
        for (Class<?> cls : clsList){
            // 判断是否是controller
            if (cls.isAnnotationPresent(Controller.class)){
                parseHandlerFromController(cls);
            }
        }
    }

    /**
     * 对类进行提取
     * @param cls
     */
    public static void parseHandlerFromController(Class<?> cls){
        // 获取类下所有的方法
        Method[] methods = cls.getDeclaredMethods();
        // 遍历
        for (Method method : methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            //请求路径
            String uri = method.getDeclaredAnnotation(RequestMapping.class).url();
            // 方法中的参数
            List<String> paramNameList = new ArrayList<>();
            for (Parameter parameter : method.getParameters()){
                if (parameter.isAnnotationPresent(RequestParam.class)){
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);
            MappingHandler mappingHandler = new MappingHandler(uri,method,cls,params);
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
