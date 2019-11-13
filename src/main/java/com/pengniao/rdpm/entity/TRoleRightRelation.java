package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 角色-权限
 * @author: lj
 * @create: 2019-10-24 15:24
 **/
@Component
public class TRoleRightRelation {

    private String trrId; //记录标识
    private String roleId; // 角色ID
    private String rightId; // 权限ID

    public String getTrrId() {
        return trrId;
    }

    public void setTrrId(String trrId) {
        this.trrId = trrId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }
}
