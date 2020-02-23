package com.sun.nss.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户token
 * @author zcm
 */
@Data
@ApiModel(value = "SysUserToken对象",description = "系统用户token")
public class SysUserToken implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "token")
    private String token;
}
