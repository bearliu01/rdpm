package com.pengniao.rdpm.dto;

import com.pengniao.rdpm.entity.TUser;

/**
 * @program: rdpm
 * @description: 用户DTO
 * @author: lj
 * @create: 2019-10-28 10:28
 **/
public class TUserDTO extends TUser {

    private String roleIds; //用户角色（多选后ID字符串）

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
