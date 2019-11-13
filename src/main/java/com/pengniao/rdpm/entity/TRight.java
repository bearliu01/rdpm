package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 权限
 * @author: lj
 * @create: 2019-10-22 09:28
 **/
@Component
public class TRight {
    private String rightId; // 权限ID
    private String parentTrId; // 父权限ID
    private String rightName; // 权限名称
    private String action; // 对应的action

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getParentTrId() {
        return parentTrId;
    }

    public void setParentTrId(String parentTrId) {
        this.parentTrId = parentTrId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
