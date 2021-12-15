package com.sc.vo;

public class Page {
    //每页的记录数
    public static final  int PAGESIZE = 5;
    //当前页默认为第一页
    private int currentPage = 1;
    //总记录数
    private int totalCount;
    //总页数
    private int totalPageNum;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }
}
