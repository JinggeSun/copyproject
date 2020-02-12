package com.sun.mini.web.handler;

import com.sun.mini.beans.BeanFactory;
import com.sun.mini.web.mvc.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zcm
 */
public class MappingHandler {

    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    MappingHandler(String uri, Method method, Class<?> controller, String[] args) {
        this.uri = uri;
        this.method = method;
        this.controller = controller;
        this.args = args;
    }

    public boolean handle(ServletRequest req, ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requesturi = ((HttpServletRequest)req).getRequestURI();
        if (!uri.equals(requesturi)){
            return false;
        }
        Object[] parameters = new Object[args.length];
        for (int i=0;i<args.length;i++){
            parameters[i] = req.getParameter(args[i]);
        }
        Object ctl = BeanFactory.getBean(controller);
        Object response = method.invoke(ctl,parameters);
        res.getWriter().println(response.toString());
        return true;
    }
}
