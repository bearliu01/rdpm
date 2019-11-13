package com.pengniao.rdpm.system.service;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.entity.TUserRoleRelation;

import java.util.List;

/** 功能描述: 角色
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/23 17:22
*/
public interface RoleService {

    /* 功能描述: 分页显示角色
     * @param startRow 开始行
     * @param rows 每页显示行数
     * @param enterpriseId 企业ID
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/25 14:16
    */
    public List<TRole> getRole(Integer startRow, Integer rows, String enterpriseId);

    /** 功能描述: 通过企业ID获取角色
     * @param enterpriseId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/28 11:39
    */
    public List<TRole> getRoleAll();

    /** 功能描述: 查询角色总数
     * @param enterpriseId 企业ID
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/25 14:50
    */
    public Integer queryRoleCount(String enterpriseId);

    /** 功能描述: 新增角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 17:23
    */
    public void addRole(TRole role)throws Exception;

    /** 功能描述: 更新角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:26
    */
    public void updateRole(TRole role)throws Exception;

    /** 功能描述: 通过角色ID删除角色
     * @param roleId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:39
    */
    public void deleteRole(String roleId)throws Exception;


    /** 功能描述: 新增管理员 - 角色
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/23 17:44
    */
    public void addUserRoleRelation(TUserRoleRelation userRoleRelation)throws Exception;

    /** 功能描述: 通过管理员ID删除用户角色
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:45
    */
    public void deleteUserRoleRelationByUserId(String userId)throws Exception;

    /** 功能描述: 通过用户ID获取用户角色
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TUserRoleRelation>
    * @Author: lj
    * @Date: 2019/10/24 12:00
    */
    public List<TUserRoleRelation> getUserRoleRelationByUserId(String userId);

    /** 功能描述: 获取管理员的 用户-角色信息
     * @param userId 管理员ID
    * @return: com.pengniao.rdpm.entity.TUserRoleRelation
    * @Author: lj
    * @Date: 2019/10/24 17:50
    */
    public TUserRoleRelation getAdministratorUserRoleRelation(String userId);

    /** 功能描述: 通过角色ID 获取角色
     * @param roleId
    * @return: com.pengniao.rdpm.entity.TRole
    * @Author: lj
    * @Date: 2019/10/25 16:01
    */
    public TRole getRoleById(String roleId);

}
