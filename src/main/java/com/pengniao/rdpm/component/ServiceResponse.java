package com.pengniao.rdpm.component;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 返回结果
 * @author: lj
 * @create: 2019-09-24 16:17
 **/
@Component
public class ServiceResponse {

    private String code;
    private String msg;
    private Object object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
