package com.sun.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole {

    private Integer id;
    private Integer userId;
    private Integer roleId;
}
