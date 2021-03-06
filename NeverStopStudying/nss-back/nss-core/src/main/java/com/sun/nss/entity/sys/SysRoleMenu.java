package com.sun.nss.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应表
 * @author zcm
 */
@Data
@ApiModel(value = "SysRoleMenu对象",description = "角色与菜单对应关系")
public class SysRoleMenu implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;
}
