package com.pengniao.rdpm.system.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rdpm
 * @description: 企业维护
 * @author: lj
 * @create: 2019-10-17 15:27
 **/
@RestController
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ServiceResponse serviceResponse;

    /** 功能描述: 分页获取企业
     * @param pagination
     * @param page
     * @param rows
    * @return: com.pengniao.rdpm.component.GridData<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/18 11:46
    */
    @RequestMapping("system/enterprise/getEnterprise")
    public GridData<Enterprise> getEnterprise(Pagination pagination, Integer page, Integer rows)
    {
        List<Enterprise> enterpriseList= enterpriseService.getEnterprise(pagination.getStartRow(), pagination.getRows()); //获取数据
        Integer count = enterpriseService.queryEnterpriseCount(); //获取数据总记录数
        pagination.setRecords(count);
        GridData<Enterprise> gridData = new GridData<Enterprise>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(enterpriseList);
        return gridData;
    }

    /** 功能描述: 新增企业
     * @param enterprise
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/18 9:50
    */
    @RequestMapping("system/enterprise/addEnterprise")
    public Object addEnterprise(Enterprise enterprise) throws Exception{
        enterpriseService.addEnterprise(enterprise);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 更新企业
     * @param enterprise
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/18 17:14
    */
    @RequestMapping("system/enterprise/updateEnterprise")
    public Object updateEnterprise(Enterprise enterprise) throws Exception{
        enterpriseService.updateEnterprise(enterprise);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 更新企业基本信息
     * @param enterprise
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/30 14:59
    */
    @RequestMapping("system/enterprise/updateEnterpriseBase")
    public Object updateEnterpriseBase(Enterprise enterprise) throws Exception{
        enterpriseService.updateEnterpriseBase(enterprise);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 删除企业
     * @param enterpriseId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/21 9:57
    */
    @RequestMapping("system/enterprise/deleteEnterprise")
    public Object deleteEnterprise(String enterpriseId) throws Exception{
        enterpriseService.deleteEnterprise(enterpriseId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }

    /** 功能描述: 启用|停用 企业
     * @param enterpriseId 企业ID
     * @param enterpriseIsUse 0-启用  1-停用
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/21 10:58
    */
    @RequestMapping("system/enterprise/updateIsUse")
    public Object updateIsUse(String enterpriseId, Integer enterpriseIsUse) throws Exception{
        enterpriseService.updateIsUse(enterpriseId, enterpriseIsUse);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("设置成功");
        return serviceResponse;
    }
}
