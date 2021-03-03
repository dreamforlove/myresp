package com.engineer.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Lemon
 * @date 2019/8/4 14:27
 */
@Data
public class PageResult<T> {
    /**
     * 当前页数
     */
    private int pageNum;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 当前页数据
     */
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
