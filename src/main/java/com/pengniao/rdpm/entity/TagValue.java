package com.pengniao.rdpm.entity;

import java.io.Serializable;

/**
 * @program: rdpm
 * @description: 用于组态图显示
 * @author: lj
 * @create: 2019-10-09 14:42
 **/
public class TagValue implements Serializable {

    private String id;
    private String plug;
    private String unit;
    private Object value;
    private Double max;
    private Double min;
    private String time;
    private String desc;
    private int status;
    private int alarm;
    private int digcount;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlug() {
        return plug;
    }

    public void setPlug(String plug) {
        this.plug = plug;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getDigcount() {
        return digcount;
    }

    public void setDigcount(int digcount) {
        this.digcount = digcount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
