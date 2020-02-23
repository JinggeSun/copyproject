package com.sun.nss.common.enums;

import lombok.Getter;

/**
 * 分类类别枚举
 * @author zcm
 */
@Getter
public enum  CategoryRankEnum {

    /**
     * 一级
     */
    ROOT(-1),
    /**
     * 一级
     */
    FIRST(0),
    /**
     * 二级
     */
    SECOND(1),

    /**
     * 三级
     */
    THIRD(2),
    ;

    private int value;

    CategoryRankEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
