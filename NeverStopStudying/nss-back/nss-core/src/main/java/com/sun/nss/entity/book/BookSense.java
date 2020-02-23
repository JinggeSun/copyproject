package com.sun.nss.entity.book;

import com.sun.nss.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 读后感
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "BookSense对象",description = "读后感")
public class BookSense extends BaseEntity implements Serializable {

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("关联图书ID")
    private Integer bookId;
}
