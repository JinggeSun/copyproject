package com.sun.nss.entity.book.vo;

import com.sun.nss.entity.book.BookNote;
import com.sun.nss.entity.book.BookSense;
import com.sun.nss.entity.operation.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.print.Book;
import java.util.List;

/**
 *
 * @author zcm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookVO extends Book {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属笔记
     */
    private List<BookNote> bookNoteList;

    /**
     * 读后感
     */
    private BookSense bookSense;
}