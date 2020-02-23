package com.sun.nss.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * 分页插件工具类
 * @author zcm
 */
@Data
@NoArgsConstructor
public class PageUtils {

    private long totalCount;

    private long pageSize;

    private long totalPage;

    private long currPage;

    private List<?> list;

    public PageUtils(List<?> list,int totalCount,int pageSize,int currPage){
        this.list  = list;
        this.totalCount = totalPage;
        this.pageSize = pageSize;
        this.currPage = currPage;

        this.totalPage = (long) Math.ceil((double) totalCount/pageSize);

    }

    /**
     * 分页
     */
    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (int)page.getPages();
    }
}
