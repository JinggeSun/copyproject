package com.sun.learn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("account")
public class Account extends BaseEntity{


    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    private String salt;

    private int status;
}
