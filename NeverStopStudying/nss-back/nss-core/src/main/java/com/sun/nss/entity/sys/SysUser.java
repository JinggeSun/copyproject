package com.sun.nss.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zcm
 */
@Data
@ApiModel(value = "SysUser对象",description = "用户管理")
public class SysUser implements Serializable {

    @TableId(value = "user_id",type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String username;

    @NotBlank(message = "密码不能为空",groups = AddGroup.class)
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Email(message = "邮箱格式不正确",groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "创建者ID")
    private Integer createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "0禁用，1正常")
    private Integer status;

    @TableField(exist = false)
    private List<Integer> roleIdList;
}
