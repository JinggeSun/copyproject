package com.sun.nss.entity.article;

import com.sun.nss.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 文章
 * @author zcm
 */
@Data
@ApiModel(value = "BlogArticle对象",description = "文章")
@Document(indexName = "dblog",type = "article")
public class Article extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String title;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message = "文章内容不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String content;


    @ApiModelProperty(value = "阅读量")
    private Long readNum;

    @ApiModelProperty(value = "点赞量")
    private Long likeNum;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "文章展示类别,0:普通，1：大图片，2：无图片")
    private Integer coverType;

    @ApiModelProperty(value = "是否推荐文章")
    private Boolean recommend;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "发布状态")
    private Boolean publish;

    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

    @ApiModelProperty(value = "格式化后的内容")
    private String contentFormat;
}
