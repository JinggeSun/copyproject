package com.sun.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zcm
 */

@Getter
@AllArgsConstructor
public enum SchedulingEnums {

    /**
     * 正常
     */
    NORMAL (0),
    /**
     * 暂停
     */
    STOP (1),
    ;

    private Integer code;
}
