package com.sun.rpc.codec;

/**
 * 反序列化
 * @author zcm
 */
public interface Decoder {

    /**
     * 将byte数组转为object
     * @param bytes
     * @return
     */
    Object decoder(byte[] bytes);

    /**
     * 反序列化
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decoder(byte[] bytes,Class<T> clazz);
}
