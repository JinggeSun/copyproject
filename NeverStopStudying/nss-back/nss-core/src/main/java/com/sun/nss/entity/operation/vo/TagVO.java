package com.sun.nss.entity.operation.vo;

import com.sun.nss.entity.operation.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagVO extends Tag {

    /**
     *
     */
    private String linkNum;
}
