package com.pengniao.rdpm.system.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRight;
import com.pengniao.rdpm.system.service.EnterpriseService;
import com.pengniao.rdpm.system.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rdpm
 * @description: 权限
 * @author: lj
 * @create: 2019-10-22 10:13
 **/
@RestController
public class RightController {
    @Autowired
    private RightService rightService;
    @Autowired
    private ServiceResponse serviceResponse;

    /** 功能描述: 获取权限
     * @param 
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/22 10:15
    */
    @RequestMapping("system/right/getRight")
    public Object getRight()throws Exception
    {
        Object object = rightService.getRight();
        return object;
    }

    /** 功能描述: 新增权限
     * @param right
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/22 14:33
    */
    @RequestMapping("system/right/addRight")
    public Object addRight(TRight right) throws Exception{
        rightService.addRight(right);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述:  修改权限
     * @param right
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/22 15:30
    */
    @RequestMapping("system/right/updateRight")
    public Object updateRight(TRight right) throws Exception{
        rightService.updateRight(right);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("修改成功");
        return serviceResponse;
    }

     /** 功能描述: 删除权限
      * @param rightId 权限ID
     * @return: java.lang.Object
     * @Author: lj
     * @Date: 2019/10/22 15:46
     */
    @RequestMapping("system/right/deleteRight")
    public Object deleteRight(Integer rightId) throws Exception{
        List<TRight> rightList= rightService.getChildRightById(rightId);
        if(((List) rightList).size()>0){
            throw new Exception("存在子节点，请先删除子节点");
        }
        rightService.deleteRight(rightId);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }

    /** 功能描述: 通过角色ID获取权限
     * @param roleId 角色ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 15:54
    */
    @RequestMapping("system/right/getRightByRoleId")
    public Object getRightByRoleId(String roleId){
        return rightService.getRight(roleId);
    }

    /** 功能描述: 通过管理员ID获取管理员权限
     * @param userId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 9:05
    */
    @RequestMapping("system/right/getAdministratorRightByUserId")
    public Object getAdministratorRightByUserId(String userId){
        return rightService.getAdministratorRight(userId);
    }

    /** 功能描述: 通过角色ID获取权限
     * @param roleId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 17:39
    */
    @RequestMapping("system/right/getRoleRightByRoleId")
    public Object getRoleRightByRoleId(String roleId){
        return rightService.getRight(roleId);
    }


    /** 功能描述: 设置管理员权限
     * @param userId 管理员ID
     * @param rightStr
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 17:58
    */
    @RequestMapping("system/right/setAdministratorRight")
    public Object setAdministratorRight(String userId,String rightStr) throws Exception{
        rightService.insertAdministratorRoleRight(userId,rightStr);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("设置成功");
        return serviceResponse;
    }

    /** 功能描述: 设置权限
     * @param roleId 角色ID
     * @param rightStr 选择的权限ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 17:03
    */
    @RequestMapping("system/right/setRoleRight")
    public Object setRoleRight(String roleId,String rightStr) throws Exception{
        rightService.insertRoleRight(roleId,rightStr);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("设置成功");
        return serviceResponse;
    }

}
