package com.pengniao.rdpm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @program: rdpm
 * @description: 组态返回参数
 * @author: lj
 * @create: 2019-10-09 16:04
 **/
public class TagsTable implements Serializable {

    private int total;

    private List<TagValue> rows;

    private String login;

    private String time;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TagValue> getRows() {
        return rows;
    }

    public void setRows(List<TagValue> rows) {
        this.rows = rows;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
