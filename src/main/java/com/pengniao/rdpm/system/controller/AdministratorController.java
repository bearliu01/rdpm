package com.pengniao.rdpm.system.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.system.service.AdministratorService;
import com.pengniao.rdpm.system.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rdpm
 * @description: 企业管理员
 * @author: lj
 * @create: 2019-10-23 09:21
 **/
@RestController
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ServiceResponse serviceResponse;

    /** 功能描述: 分页获取企业管理员
     * @param pagination
     * @param page
     * @param rows
    * @return: com.pengniao.rdpm.component.GridData<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/23 9:23
    */
    @RequestMapping("system/administrator/getAdministrator")
    public GridData<TUser> getAdministrator(Pagination pagination, Integer page, Integer rows)
    {
        List<TUser> administratorList= administratorService.getAdministrator(pagination.getStartRow(), pagination.getRows()); //获取数据
        Integer count = administratorService.queryAdministratorCount(); //获取数据总记录数
        pagination.setRecords(count);
        GridData<TUser> gridData = new GridData<TUser>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(administratorList);
        return gridData;
    }

    /** 功能描述: 生成企业管理员登录账号
     * @param enterpriseId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/23 14:52
    */
    @RequestMapping("system/administrator/generateAccount")
    public Object generateAccount(String enterpriseId) throws Exception{
        if(administratorService.getAdministratorByEnterpriseId(enterpriseId) !=null){
            throw new Exception("已存在企业管理员");
        }
        //如果当前企业还没有企业管理员，那么获取当前企业的英文简称+随机号生成管理员账号
        Enterprise enterprise = enterpriseService.getEnterpriseById(enterpriseId);
        String loginName = enterprise.getEnterpriseEnglishName() +Tools.getFourRandom(); //生成登录账号
        serviceResponse.setCode("success");
        serviceResponse.setMsg(loginName);
        return serviceResponse;
    }

    /** 功能描述: 新增企业管理员
     * @param user
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 10:53
    */
    @RequestMapping("system/administrator/addAdministrator")
    public Object addAdministrator(TUser user) throws Exception{
        administratorService.addAdministrator(user);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 更新企业管理员
     * @param user
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 10:53
    */
    @RequestMapping("system/administrator/updateAdministrator")
    public Object updateAdministrator(TUser user) throws Exception{
        administratorService.updateAdministrator(user);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 删除企业管理员
     * @param userId 用户ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 11:12
    */
    @RequestMapping("system/administrator/deleteAdministrator")
    public Object deleteAdministrator(String userId) throws Exception{
        administratorService.deleteAdministratorById(userId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }

    /** 功能描述: 更改管理员状态
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/24 14:13
    */
    @RequestMapping("system/administrator/updateAdministratorIsUse")
    public Object updateAdministratorIsUse(Integer isUse, String userId) throws Exception{
        administratorService.updateAdministratorIsUse(isUse, userId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更改成功");
        return serviceResponse;
    }


}
