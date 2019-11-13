package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: demo
 * @author: lj
 * @create: 2019-09-24 09:45
 **/
@Component
public class Demo {

    private String id;

    private String name;

    private Integer age;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
