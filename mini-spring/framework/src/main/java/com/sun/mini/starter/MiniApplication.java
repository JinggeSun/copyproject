package com.sun.mini.starter;

import com.sun.mini.beans.BeanFactory;
import com.sun.mini.core.ClassScanner;
import com.sun.mini.web.handler.HandlerManager;
import com.sun.mini.web.server.TomcatServer;

import java.util.List;

/**
 * @author zcm
 */
public class MiniApplication {
    public static void run(Class<?> cls , String[] args){
        System.out.println("启动 mini-spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();

            List<Class<?>> classList = ClassScanner.scannerClasses(cls.getPackage().getName());
            classList.forEach((s)-> System.out.println(s.getName()));
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
