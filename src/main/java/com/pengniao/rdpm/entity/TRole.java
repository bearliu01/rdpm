package com.pengniao.rdpm.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 角色
 * @author: lj
 * @create: 2019-10-23 16:30
 **/
@Component
public class TRole {
    private String roleId; //角色ID
    private String roleName; //角色名称
    private String createTime; // 创建时间
    private String description; // 描述
    private String enterpriseId; //企业ID
    private Enterprise enterprise; //所属企业
    private Integer isAdminRole; //是否企业管理员角色

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Integer getIsAdminRole() {
        return isAdminRole;
    }

    public void setIsAdminRole(Integer isAdminRole) {
        this.isAdminRole = isAdminRole;
    }
}
