package com.sun.nss.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.PipedReader;
import java.io.Serializable;

/**
 * 友链
 * @author zcm
 */
@Data
@ApiModel(value="Link对象", description="友链")
@Accessors(chain = false)
@EqualsAndHashCode(callSuper = false)
public class Link implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty(value = "链接名称")
    private String title;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "头像")
    private String avatar;

}
