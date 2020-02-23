package com.sun.nss.entity.article.dto;

import com.sun.nss.entity.article.Article;
import com.sun.nss.entity.operation.Tag;
import lombok.Data;

import java.util.List;


/**
 * 传输层
 * @author zcm
 */
@Data
public class ArticleDTO extends Article {

    private List<Tag> tagList;
}
