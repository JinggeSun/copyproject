package com.sun.nss.entity.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登陆表单
 * @author zcm
 */
@Data
@ApiModel(value = "SysLoginForm对象",description = "登陆表单")
public class SysLoginForm {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "uuid")
    private String uuid;
}
