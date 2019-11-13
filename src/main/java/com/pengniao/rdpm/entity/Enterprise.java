package com.pengniao.rdpm.entity;

import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description: 企业维护
 * @author: lj
 * @create: 2019-10-17 15:15
 **/
@Component
public class Enterprise {

    private String enterpriseId; //企业ID
    private String enterpriseName; //企业名称
    private String enterpriseAddr; //企业地址
    private String enterpriseLinkMen; //联系人
    private String enterpriseTel; //联系电话
    private String enterpriseRegDate; //注册时间
    private String enterpriseEndDate; //截至时间
    private Integer enterpriseIsUse; //是否停用
    private String createDate; // 创建日期
    private String enterpriseEnglishName; //企业英文简称
    private String enterpriseLogo;//企业logo

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getEnterpriseAddr() {
        return enterpriseAddr;
    }

    public void setEnterpriseAddr(String enterpriseAddr) {
        this.enterpriseAddr = enterpriseAddr == null ? null : enterpriseAddr.trim();
    }

    public String getEnterpriseLinkMen() {
        return enterpriseLinkMen;
    }

    public void setEnterpriseLinkMen(String enterpriseLinkMen) {
        this.enterpriseLinkMen = enterpriseLinkMen == null ? null : enterpriseLinkMen.trim();
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel == null ? null : enterpriseTel.trim();
    }

    public String getEnterpriseRegDate() {
        return enterpriseRegDate;
    }

    public void setEnterpriseRegDate(String enterpriseRegDate) {
        this.enterpriseRegDate = enterpriseRegDate;
    }

    public String getEnterpriseEndDate() {
        return enterpriseEndDate;
    }

    public void setEnterpriseEndDate(String enterpriseEndDate) {
        this.enterpriseEndDate = enterpriseEndDate;
    }

    public Integer getEnterpriseIsUse() {
        return enterpriseIsUse;
    }

    public void setEnterpriseIsUse(Integer enterpriseIsUse) {
        this.enterpriseIsUse = enterpriseIsUse;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEnterpriseEnglishName() {
        return enterpriseEnglishName;
    }

    public void setEnterpriseEnglishName(String enterpriseEnglishName) {
        this.enterpriseEnglishName = enterpriseEnglishName == null ? null : enterpriseEnglishName.trim();
    }

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseAddr='" + enterpriseAddr + '\'' +
                ", enterpriseLinkMen='" + enterpriseLinkMen + '\'' +
                ", enterpriseTel='" + enterpriseTel + '\'' +
                ", enterpriseRegDate='" + enterpriseRegDate + '\'' +
                ", enterpriseEndDate='" + enterpriseEndDate + '\'' +
                ", enterpriseIsUse=" + enterpriseIsUse +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
