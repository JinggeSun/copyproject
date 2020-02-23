package com.sun.nss.entity.timeline;

import lombok.Data;

import java.util.Date;

/**
 * @author zcm
 */
@Data
public class TimelinePost {

    private Integer id;

    private String title;

    private String description;

    private String postType;

    private Date createTime;
}
