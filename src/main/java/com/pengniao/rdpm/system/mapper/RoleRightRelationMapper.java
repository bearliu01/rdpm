package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.TRoleRightRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 角色-权限
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/24 15:28
*/
@Mapper
public interface RoleRightRelationMapper {

    /** 功能描述: 通过角色ID获取角色权限
     * @param roleId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRoleRightRelation>
    * @Author: lj
    * @Date: 2019/10/24 15:30
    */
    public List<TRoleRightRelation> getRoleRightByRoleId(String roleId);

    /** 功能描述: 通过角色ID删除角色权限
     * @param roleId 角色ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 16:41
    */
    public void deleteRoleRightByRoleId(String roleId)throws Exception;
    
    /** 功能描述: 新增角色权限
     * @param roleRightList
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 16:48
    */
    public void insertRolesRightBatch(List<TRoleRightRelation> roleRightList)throws Exception;

}
