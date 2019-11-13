package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.entity.TUserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 角色
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/23 17:14
*/
@Mapper
public interface RoleMapper {

    /** 功能描述: 分页显示企业角色
     * @param startRow 起始页
     * @param limit 每页行数
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/25 14:20
    */
    public List<TRole> getRole(@Param("startRow") Integer startRow, @Param("limit") Integer limit, @Param("enterpriseId") String enterpriseId);

    /** 功能描述: 查询企业角色总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/25 14:30
    */
    public Integer queryRoleCount(String enterpriseId);

    /** 功能描述: 通过企业ID获取角色(除企业管理员外)
     * @param enterpriseId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/28 11:38
    */
    public List<TRole> getRoleAll(String enterpriseId);

    /** 功能描述: 新增角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 17:18
    */
    public void addRole(TRole role)throws Exception;

    /** 功能描述: 更新角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:23
    */
    public void updateRole(TRole role)throws Exception;

    /** 功能描述: 通过角色ID删除角色
     * @param roleId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:37
    */
    public void deleteRole(String roleId)throws Exception;

    /** 功能描述: 新增用户角色
     * @param userRoleRelation
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 17:42
    */
    public void addUserRoleRelation(TUserRoleRelation userRoleRelation)throws Exception;

    /** 功能描述: 批量新增用户角色
     * @param userRoleRelationList
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 10:39
    */
    public void BatchAddUserRoleRelation(List<TUserRoleRelation> userRoleRelationList)throws Exception;

    /** 功能描述: 通过用户ID删除角色
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:51
    */
    public void deleteUserRoleRelationByUserId(String userId)throws Exception;

    /** 功能描述: 通过用户ID获取用户角色
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TUserRoleRelation>
    * @Author: lj
    * @Date: 2019/10/24 11:53
    */
    public List<TUserRoleRelation> getUserRoleRelationByUserId(String userId);

    /** 功能描述: 通过用户ID获取角色
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/28 14:27
    */
    public List<TRole> getRoleByUserId(String userId);

    /** 功能描述: 获取管理员的 用户-角色信息
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUserRoleRelation
    * @Author: lj
    * @Date: 2019/10/24 17:48
    */
    public TUserRoleRelation getAdministratorUserRoleRelation(String userId);

    /** 功能描述: 批量删除角色
     * @param userRoleRelationList 用户-角色
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 12:18
    */
    public void batchDeleteRoleById(List<TUserRoleRelation> userRoleRelationList);

    /** 功能描述: 通过角色ID 获取角色
     * @param roleId
    * @return: com.pengniao.rdpm.entity.TRole
    * @Author: lj
    * @Date: 2019/10/25 15:59
    */
    public TRole getRoleById(String roleId);


}
