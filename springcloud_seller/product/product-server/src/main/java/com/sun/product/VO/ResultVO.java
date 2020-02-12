package com.sun.product.VO;

import lombok.Data;

/**
 * @author zcm
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;

}
