package com.sun.nss.entity.book.dto;

import com.sun.nss.entity.book.BOOK;
import com.sun.nss.entity.operation.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 *
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookDTO extends BOOK {
    List<Tag> tagList;

}
