package com.sun.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu {

    private Integer id;
    private Integer roleId;
    private Integer menuId;

}
