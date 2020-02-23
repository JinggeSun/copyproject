package com.sun.rpc.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 序列化
 * @author zcm
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encoder(Object object) {
        return JSON.toJSONBytes(object);
    }
}
