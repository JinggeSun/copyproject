package com.sun.nss.entity.operation.vo;

import com.sun.nss.entity.operation.Tag;
import lombok.Data;
import sun.dc.pr.PRError;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zcm
 */
@Data
public class RecommendVO implements Serializable {

    private String description;
    private Long readNum;
    private Long commentNum;
    private Long linkNum;
    private String urlType;
    private String cover;
    private Date createTime;
    private List<Tag> tagList;
}
