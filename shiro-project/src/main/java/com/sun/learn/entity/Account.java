package com.sun.learn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("account")
public class Account {

    private int id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

}
