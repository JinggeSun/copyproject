package com.sun.learn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
import java.time.LocalDateTime;

/**
 * @author zcm
 */
@Data
public class BaseEntity {

    @TableId
    private Integer id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
