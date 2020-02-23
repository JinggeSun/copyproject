package com.sun.rpc.transport;

/**
 * 服务器传输
 * @author zcm
 */
public interface TransportServer {

    /**
     * 初始化
     * @param port
     * @param requestHandler
     */
    void init(int port,RequestHandler requestHandler);

    /**
     * 开始
     */
    void start();

    /**
     * 停止
     */
    void stop();
}
