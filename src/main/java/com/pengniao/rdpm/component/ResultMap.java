package com.pengniao.rdpm.component;

/**
 * @program: rdpm
 * @description: layui表格数据封装
 * @author: lj
 * @create: 2019-09-25 10:34
 **/
public class ResultMap<T> {

    public ResultMap(String msg, T data, int code, int count) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public ResultMap() {
    }

    /**
     * @Fields msg : 返回的消息
     */
    private String msg;
    /**
     * @Fields data : 表格显示的数据
     */
    private  T data;
    /**
     * @Fields code : code（要为0，不然数据表格数据显示不出）
     */
    private  Integer code;
    /**
     * @Fields count : 查询到数据的总记录数
     */
    private  Integer count;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
