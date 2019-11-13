package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: rdpm
 * @description: 用户
 * @author: lj
 * @create: 2019-10-21 17:03
 **/
@Component
public class TUser {

    private String userId; // 标识ID
    private String enterpriseId;// 所属企业
    private String loginName;// 登录账号
    private String password;// 密码
    private String userName;// 用户姓名
    private String mobile;// 手机号
    private String email;// 邮箱
    private String createTime;// 创建时间
    private Integer isAdmin;// 是否管理员
    private Integer isUse; //是否停用 0-启用  1-停用
    private String noEncryPassword;//未加密密码
    private List<TRole> roleList; //角色

    private  Enterprise enterprise;//所属企业

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public String getNoEncryPassword() {
        return noEncryPassword;
    }

    public void setNoEncryPassword(String noEncryPassword) {
        this.noEncryPassword = noEncryPassword == null ? null : noEncryPassword.trim();
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<TRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "userId='" + userId + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isAdmin=" + isAdmin +
                ", isUse=" + isUse +
                ", noEncryPassword='" + noEncryPassword + '\'' +
                ", enterprise=" + enterprise +
                '}';
    }
}
