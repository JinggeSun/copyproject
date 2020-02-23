package com.sun.nss.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章多对多维护表
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TagLink对象",description = "标签多对多维护表")
@TableName("tag_link")
public class TagLink implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer linkId;

    private Integer tagId;

    @ApiModelProperty(value = "所属类型：0文章，1阅读")
    private Integer type;


}
