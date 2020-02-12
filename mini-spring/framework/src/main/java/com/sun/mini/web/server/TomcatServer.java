package com.sun.mini.web.server;

import com.sun.mini.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * @author zcm
 */
public class TomcatServer {

    private Tomcat tomcat;
    private String[] args;

    /**
     * 构造方法
     * @param args
     */
    public TomcatServer(String[] args){
        this.args = args;
    }

    /**
     * 启动tomcat服务
     */
    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(6699);
        tomcat.start();

        //context 容器
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        // 将servlet注册到容器里
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        Tomcat.addServlet(context,"dispatcherServlet",dispatcherServlet).setAsyncSupported(true);
        // url映射
        context.addServletMappingDecoded("/","dispatcherServlet");

        tomcat.getHost().addChild(context);
        /**
         * 设置一个线程t
         */

        Thread thread = new Thread(()->{
            TomcatServer.this.tomcat.getServer().await();
        });
        //守护
        thread.setDaemon(false);
        thread.start();

    }

}
