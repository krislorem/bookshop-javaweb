package com.example.bookshop.model;

import lombok.Data;

import java.util.List;

/**
 * @className: Page
 * @author: ZhaiJinPei
 * @discription: 页信息
 */
@Data
public class Page {
    private int pageNumber;  //当前页码
    private int pageSize;    //每页最多展示商品数
    private int totalCount;   //该类型商品总数
    private int totalPage;   //页总码

    private List<Object> list;

    public void SetPageSizeAndTotalCount(int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}