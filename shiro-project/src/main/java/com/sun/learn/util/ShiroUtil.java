package com.sun.learn.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * shiro工具类，主要是用户密码的操作。
 * @author zcm
 */
public class ShiroUtil {

    public static final String ALG_NAME = "MD5";
    public static final Integer HASH_ITERATION =  2;

    /**
     * 生成盐
     * @return
     */
    private static String getSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toString();
    }

    public static String get(String password){
        SimpleHash hash = new SimpleHash(ALG_NAME, password, getSalt(),HASH_ITERATION);
        return hash.toString();
    }
}
