package com.pengniao.rdpm.system.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rdpm
 * @description: 角色
 * @author: lj
 * @create: 2019-10-25 14:15
 **/
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ServiceResponse serviceResponse;

    /** 功能描述: 分页显示角色
     * @param pagination
     * @param page
     * @param rows
    * @return: com.pengniao.rdpm.component.GridData<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/25 14:40
    */
    @RequestMapping("system/role/getRole")
    public GridData<TRole> getRole(Pagination pagination, Integer page, Integer rows)
    {
        List<TRole> roleList= roleService.getRole(pagination.getStartRow(), pagination.getRows(), Tools.getUserSession().getEnterpriseId()); //获取数据
        Integer count = roleService.queryRoleCount(Tools.getUserSession().getEnterpriseId()); //获取数据总记录数
        pagination.setRecords(count);
        GridData<TRole> gridData = new GridData<TRole>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(roleList);
        return gridData;
    }

    /** 功能描述: 获取企业下的所有角色
     * @param 
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/28 11:42
    */
    @RequestMapping("system/role/getRoleAll")
    public List<TRole> getRoleAll()
    {
      return  roleService.getRoleAll();
    }


    /** 功能描述: 新增角色
     * @param role
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 15:20
    */
    @RequestMapping("system/role/addRole")
    public Object addRole(TRole role) throws Exception{
        roleService.addRole(role);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 更新角色
     * @param role
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 16:27
    */
    @RequestMapping("system/role/updateRole")
    public Object updateRole(TRole role) throws Exception{
        roleService.updateRole(role);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 通过角色ID删除角色
     * @param roleId 角色ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 16:43
    */
    @RequestMapping("system/role/deleteRole")
    public Object deleteRole(String roleId) throws Exception{
        roleService.deleteRole(roleId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }
}
