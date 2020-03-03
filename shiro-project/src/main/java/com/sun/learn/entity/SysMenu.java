package com.sun.learn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu extends BaseEntity{

    private Integer pId;
    @TableField(value = "name")
    private String title;
    private String perms;
    @TableField(value = "url")
    private String href;
    private Integer type;
    private Integer orderNum;
    private String target;
    private String info;
    private String icon;

    @TableField(exist = false)
    private List<SysMenu> child;
}
