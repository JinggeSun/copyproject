package com.sun.nss.common.enums;

import lombok.Getter;

/**
 * @author zcm
 */
@Getter
public enum  MenuTypeEnum {

    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2),
    ;

    private int value;

    MenuTypeEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
