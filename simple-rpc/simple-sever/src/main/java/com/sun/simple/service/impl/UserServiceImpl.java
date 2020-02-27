package com.sun.simple.service.impl;

import com.sun.simple.api.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息实现类
 * @author zcm
 */
public class UserServiceImpl implements UserService {

    private static Map<Integer,String> userMap;

    static {
        userMap = new HashMap<Integer, String>(2);
        userMap.put(1,"郭靖");
        userMap.put(2,"黄蓉");
    }

    @Override
    public String getUserName(Integer id) {
        if (userMap.containsKey(id)){
            return userMap.get(id);
        }
        return null;
    }
}
