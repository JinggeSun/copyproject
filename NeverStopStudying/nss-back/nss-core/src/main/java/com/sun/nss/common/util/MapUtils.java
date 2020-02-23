package com.sun.nss.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用map
 * 其实可以使用谷歌的工具包
 * @author zcm
 */
public class MapUtils extends HashMap<String,Object> {

    @Override
    public Object put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
