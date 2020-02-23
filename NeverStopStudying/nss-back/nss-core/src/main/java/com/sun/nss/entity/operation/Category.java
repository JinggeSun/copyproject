package com.sun.nss.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zcm
 */
@Data
@TableName("category")
@ApiModel(value = "Category对象",description = "分类字典")
public class Category implements Serializable {

    @ApiModelProperty("主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("类型")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty("级别")
    @NotNull(message = "级别不能为空")
    private Integer rank;

    @ApiModelProperty("父主键")
    @NotNull(message = "父主键不能为空")
    private Integer parentId;

    @TableField(exist = false)
    private String parentName;

}
