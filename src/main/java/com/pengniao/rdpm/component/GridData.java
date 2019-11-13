package com.pengniao.rdpm.component;

import java.util.List;

/**
 * @program: rdpm
 * @description: jqGrid表格返回数据封装
 * @author: lj
 * @create: 2019-09-25 11:02
 **/
public class GridData<T> {

    /**
     * 每页显示记录数
     */
    private Integer total;
    /**
     * 当前第几页
     */
    private int page;
    /**
     * 总记录数
     */
    private Integer records;

    private Pagination pagination;

    public GridData() {

    }

    public GridData(Pagination _pagination) {
        this.pagination = _pagination;
        this.total = _pagination.getTotal();
        this.page = _pagination.getPage();
        this.records = _pagination.getRecords();
    }
    public  void setTotal(Integer _total) {
        this.total = _total;

    }
    public  Integer getTotal() {
        return this.total;
    }

    public void setPage(int _page) {
        this.page = _page;
    }

    public  int getPage() {
        return this.page;
    }

    public void setRecords(Integer _records) {
        this.records = _records;
    }

    public  Integer getRecords() {
        return this.records;
    }

    public  Pagination getPagination() {
        return this.pagination;
    }

     /**
     * 要展示（包装）的实体对象集合
     */
     private List<T> rows;

    public void setRows(List<T> _rows) {
        this.rows = _rows;
    }

    public List<T> getRows() {
        return this.rows;
    }

}
