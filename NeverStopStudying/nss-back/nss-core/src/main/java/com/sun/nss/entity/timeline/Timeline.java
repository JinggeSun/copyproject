package com.sun.nss.entity.timeline;

import lombok.Data;

import java.util.List;

/**
 * @author zcm
 */
@Data
public class Timeline {

    private Integer year;

    private Integer count;

    private List<TimelineMonth> months;
}
