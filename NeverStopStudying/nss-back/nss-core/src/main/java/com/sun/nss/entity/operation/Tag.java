package com.sun.nss.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签
 * @author zcm
 */
@Data
@ApiModel(value = "Tag对象",description = "标签")
@TableName("tag")
public class Tag implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "所属类型，0文章，1阅读")
    private Integer type;
}
