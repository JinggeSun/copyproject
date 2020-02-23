package com.sun.nss.entity.article.vo;

import com.sun.nss.entity.article.Article;
import com.sun.nss.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author zcm
 */
@Data
public class ArticleVO extends Article {

    /**
     * 分类字符串
     */
    private String categoryListStr;

    /**
     * 标签列表
     */
    private List<Tag> tagList;
}
