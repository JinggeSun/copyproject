package com.sun.nss.entity.book;

import com.sun.nss.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书表
 * @author zcm
 */
@Data
@ApiModel(value = "BOOK对象",description = "图书表")
public class BOOK extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "出版社")
    private String publisher;

    @ApiModelProperty(value = "出版日期")
    private Date publishDate;

    @ApiModelProperty(value = "页数")
    private int pageNum;

    @ApiModelProperty(value = "评分")
    private Double grade;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "原书目录")
    private String catalogue;

    @ApiModelProperty(value = "阅读量")
    private Integer readNum;

    @ApiModelProperty(value = "点赞量")
    private Integer likeNum;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "是否发布")
    private Boolean publish;

    @ApiModelProperty(value = "读书进度")
    private Integer progress;

    @ApiModelProperty(value = "是否正在阅读")
    private Boolean reading;


}
