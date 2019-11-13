package com.pengniao.rdpm.exception;

import java.io.Serializable;

/**
 * @program: rdpm
 * @description: QuartzTask自定义异常
 * @author: lj
 * @create: 2019-11-05 15:10
 **/
public class QuartzTaskException extends RuntimeException implements Serializable {

    private String msg;

    public QuartzTaskException(String msg) {
        this.msg = msg;
    }

    public QuartzTaskException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
