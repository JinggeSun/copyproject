package com.sun.nss.entity.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcm
 */
@Data
@ApiModel(value = "PasswordForm",description = "修改密码表单")
public class PasswordForm {
    @ApiModelProperty(value = "旧密码")
    private String password;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
