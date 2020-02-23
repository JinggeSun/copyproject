package com.sun.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 反序列化
 * @author zcm
 */
public class JSONDecoder implements Decoder{

    @Override
    public Object decoder(byte[] bytes) {
        return JSON.parse(bytes);
    }

    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
