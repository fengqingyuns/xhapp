package com.example.demo.util;

import java.io.Serializable;

/**
 * 分页 + 数据 返回
 *
 */
public class PageDataOutput implements Serializable {

    /**  总页数*/
    private int pages;
    /**  总条数*/
    private long total;
    /**  每页的大小*/
    private int pageSize;

    /** 当前页面下标 */
    private int curPageIndex;

    private Object data;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCurPageIndex() {
        return curPageIndex;
    }

    public void setCurPageIndex(int curPageIndex) {
        this.curPageIndex = curPageIndex;
    }
}

