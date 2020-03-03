package com.sun.learn.vo;

import com.sun.learn.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVo {

    private Integer code;
    private String message;
    private Object data;

    public ResultVo(ExceptionEnums enums, Object object){
        this.code = enums.getCode();
        this.message = enums.getMessage();
        this.data = object;
    }
}
