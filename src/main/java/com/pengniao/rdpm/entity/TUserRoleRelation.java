package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 用户-角色
 * @author: lj
 * @create: 2019-10-23 17:39
 **/
@Component
public class TUserRoleRelation {

    private String turId; //记录标识
    private String userId; //用户ID
    private String roleId; //角色ID
    private TUser user; //用户
    private TRole role; //角色

    public String getTurId() {
        return turId;
    }

    public void setTurId(String turId) {
        this.turId = turId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }
}
