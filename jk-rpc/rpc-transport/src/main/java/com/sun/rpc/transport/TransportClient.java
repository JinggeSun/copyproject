package com.sun.rpc.transport;

import com.sun.rpc.Peer;

import java.io.InputStream;

/**
 * 客户端传输
 */
public interface TransportClient {

    /**
     * 连接
     * @param peer
     */
    void connect(Peer peer);

    /**
     * 关闭
     */
    void close();

    /**
     * 发送数据，并等待相应
     * @param data
     * @return
     */
    InputStream write(InputStream data);

}
