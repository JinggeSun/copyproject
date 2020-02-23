package com.sun.nss.common.enums;

import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author zcm
 */
@Getter
public enum ModuleEnum {

    /**
     * 文章
     */
    ARTICLE(0),
    /**
     * 图书模块
     */
    BOOK(1),
    /**
     * 图书笔记模块
     */
    BOOK_NOTE(2),
    ;

    private int value;

    ModuleEnum(int value){
        this.value = value;
    }
}
