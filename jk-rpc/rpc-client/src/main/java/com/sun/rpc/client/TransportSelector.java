package com.sun.rpc.client;

import com.sun.rpc.Peer;
import com.sun.rpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪个server去连接
 * @author zcm
 */
public interface TransportSelector {

    /**
     * 选择1个transport做交互
     * @return
     */
    TransportClient select();

    void release(TransportClient client);

    void close();

    /**
     * 初始化
     * @param peerList 端点
     * @param count 数量
     * @param clazz
     */
    void init(List<Peer> peerList,int count,Class<? extends TransportClient> clazz) throws IllegalAccessException;

}
