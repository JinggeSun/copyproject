package com.sun.nss.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 推荐
 * @author zcm
 */
@Data
@ApiModel(value = "Recommend对象",description = "推荐")
public class Recommend implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "文章ID")
    private Integer linkId;

    @ApiModelProperty("推荐类型")
    private Integer type;

    @ApiModelProperty("顺序")
    private Integer orderNum;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("置顶")
    private Boolean top;
}
