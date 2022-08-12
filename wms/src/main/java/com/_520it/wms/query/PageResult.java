package com._520it.wms.query;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
@Getter
public class PageResult {

    private  int totalCount;
    private List result;

    private int currentPage = 1;
    private int pageSize = 3;

    private int totalPage;
    private int prevPage;
    private int nextPage;

    public static PageResult empty(int pageSize){
        return new PageResult(0, Collections.emptyList(),1,pageSize);
    }

    public PageResult(int totalCount, List result, int currentPage, int pageSize) {
        this.totalCount = totalCount;
        this.result = result;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize + 1;
        prevPage = currentPage > 1 ? currentPage - 1 : 1;
        nextPage = currentPage < totalPage ? currentPage + 1 : totalPage;
    }
}
