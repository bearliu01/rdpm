package com.pengniao.rdpm.component;

/**
 * @program: rdpm
 * @description: jqGrid表格数据封装
 * @author: lj
 * @create: 2019-09-25 11:03
 **/
public class Pagination {
    /**
     * 每页行数
     */
    private int rows;
    /**
     * 当前页
     */
    private int page;
    /**
     * 排序列
     */
    private String sidx;
    /**
     * 排序类型
     */
    private String sord;
    /**
     * 总记录数
     */
    private Integer records;



    /**
     * 开始于第几行，由当前页和每页的记录数计算而得
     * @return
     */
    public int getStartRow() {
        return (this.getPage()-1)* this.getRows();
    }

    public int getRows() {return rows;}

    public void setRows(int _rows) {
        rows = _rows;
    }

    public int getPage() { return page; }

    public  void  setPage(int _page) {
        page =_page;

    }

    public String getSidx() { return sidx; }

    public void setSidx(String _sidx) {
        sidx = _sidx;

    }

    public String getSord() { return sord; }

    public  void setSord(String _sord) {
        sord = _sord;

    }
    /// <summary>
    /// 总记录数
    /// </summary>
    public Integer getRecords() { return records; }

    public  void setRecords(Integer _records) {
        records = _records;
    }

    /**
     * 总页数
     * @return
     */
    public Integer getTotal()
    {
        if (records > 0)
        {
            return records % this.rows == 0 ? records / this.rows : records / this.rows + 1;
        }
        else
        {
            return 0;
        }

    }

}
