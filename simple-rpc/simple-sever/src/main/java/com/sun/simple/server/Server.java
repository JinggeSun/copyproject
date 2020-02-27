package com.sun.simple.server;

import com.sun.simple.api.UserService;
import com.sun.simple.service.impl.UserServiceImpl;

import java.io.IOException;

public interface Server {

    /**
     * 启动
     */
    void start() throws IOException;

    /**
     * 关闭
     */
    void stop();

    /**
     * 获取端口
     * @return
     */
    int getPort();

    /**
     * 注册
     * @param serviceInterface
     * @param impl
     * @param <T>
     */
    void register(Class<?> serviceInterface,Class<?> impl);

    /**
     * 是否在运行
     * @return
     */
    boolean isRunning();

}
