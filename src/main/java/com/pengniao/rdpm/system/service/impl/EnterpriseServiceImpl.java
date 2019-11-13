package com.pengniao.rdpm.system.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.demo.mapper.DemoMapper;
import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.system.mapper.EnterpriseMapper;
import com.pengniao.rdpm.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: rdpm
 * @description: 企业维护
 * @author: lj
 * @create: 2019-10-18 09:43
 **/
@Transactional
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /** 功能描述: 分页获取企业
     * @param startRow 开始行
     * @param rows 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/18 11:37
    */
    @Override
    public List<Enterprise> getEnterprise(Integer startRow, Integer rows) {
        return enterpriseMapper.getEnterprise(startRow,rows);
    }

    /** 功能描述: 获取所有未停用的企业
     * @param 
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/23 11:18
    */
    @Override
    public List<Enterprise> getEnterpriseForAll() {
        return enterpriseMapper.getEnterpriseForAll();
    }

    /** 功能描述: 查询企业总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/18 11:37
    */
    @Override
    public Integer queryEnterpriseCount() {
        return enterpriseMapper.queryEnterpriseCount();
    }

    /** 功能描述: 新增企业
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/18 9:44
    */
    @Override
    public void addEnterprise(Enterprise enterprise) throws Exception {

        enterprise.setEnterpriseId(UUIDUtil.getUUID()); //企业ID
        enterprise.setEnterpriseIsUse(0); //启用
        enterprise.setCreateDate(DateTime.getNow()); //创建日期
        enterpriseMapper.addEnterprise(enterprise);
    }

    /** 功能描述: 通过企业ID获取企业信息
     * @param enterpriseId 企业ID
    * @return: com.pengniao.rdpm.entity.Enterprise
    * @Author: lj
    * @Date: 2019/10/18 14:28
    */
    @Override
    public Enterprise getEnterpriseById(String enterpriseId) {
        return enterpriseMapper.getEnterpriseById(enterpriseId);
    }

    /** 功能描述: 更新企业
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/18 17:13
    */
    @Override
    public void updateEnterprise(Enterprise enterprise) throws Exception {
        enterpriseMapper.updateEnterprise(enterprise);
    }

    /** 功能描述: 更新企业基本信息
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 14:57
    */
    @Override
    public void updateEnterpriseBase(Enterprise enterprise) throws Exception {
        enterpriseMapper.updateEnterpriseBase(enterprise);
    }

    /** 功能描述: 删除企业
     * @param enterpriseId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 9:56
    */
    @Override
    public void deleteEnterprise(String enterpriseId) throws Exception {
        enterpriseMapper.deleteEnterprise(enterpriseId);
    }

    /** 功能描述: 启用|停用 启用
     * @param enterpriseId 企业ID
     * @param enterpriseIsUse 0-启用  1-停用
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 10:55
    */
    @Override
    public void updateIsUse(String enterpriseId, Integer enterpriseIsUse) throws Exception {
        enterpriseMapper.updateIsUse(enterpriseId, enterpriseIsUse);
    }
}
