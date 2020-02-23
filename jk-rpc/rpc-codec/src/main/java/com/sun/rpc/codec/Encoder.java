package com.sun.rpc.codec;

/**
 * 序列化
 * @author zcm
 */
public interface Encoder {

    /**
     * 将object 转为byte数组
     * @param object
     * @return
     */
    byte[] encoder(Object object);
}
