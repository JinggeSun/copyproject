package com.sun.simple.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.*;

/**
 * server 主类
 * @author zcm
 */
public class ServerCenter implements Server{

    /**
     * 端口
     */
    private int port;

    /**
     * 是否正在运行
     */
    private boolean isRunning;

    /**
     * 注册服务的容器
     */
    public   static Map<String,Class<?>> serviceRegistry = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 创建线程
     */
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    ExecutorService executorService = new ThreadPoolExecutor(3,5,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);


    /**
     * 构造方法，接收port
     */
    public ServerCenter(Integer port){
        this.port = port;
    }


    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("服务启动:"+port);
        try {
            while (true){
                executorService.execute(new ServiceTask(serverSocket.accept()));
            }
        }finally {
            serverSocket.close();
        }
    }

    /**
     * 关闭
     * 关掉线程
     */
    @Override
    public void stop() {
        isRunning = false;
        executorService.shutdown();
    }

    /**
     * 获取端口
     * @return
     */
    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void register(Class<?> serviceInterface, Class<?> impl) {
        serviceRegistry.put(serviceInterface.getName(),impl);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }
}
