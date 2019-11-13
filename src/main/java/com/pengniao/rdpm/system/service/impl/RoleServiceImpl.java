package com.pengniao.rdpm.system.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.entity.TUserRoleRelation;
import com.pengniao.rdpm.system.mapper.RoleMapper;
import com.pengniao.rdpm.system.mapper.RoleRightRelationMapper;
import com.pengniao.rdpm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: rdpm
 * @description: 角色
 * @author: lj
 * @create: 2019-10-23 17:23
 **/
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRightRelationMapper roleRightRelationMapper;

    /** 功能描述: 分页显示企业角色
     * @param startRow 开始行
     * @param rows 每页显示行数
     * @param  enterpriseId 企业ID
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/25 14:17
    */
    @Override
    public List<TRole> getRole(Integer startRow, Integer rows, String enterpriseId) {
        return roleMapper.getRole(startRow, rows, enterpriseId);
    }

    /** 功能描述: 通过企业ID获取角色
     * @param
    * @return: java.util.List<com.pengniao.rdpm.entity.TRole>
    * @Author: lj
    * @Date: 2019/10/28 11:40
    */
    @Override
    public List<TRole> getRoleAll() {
        return roleMapper.getRoleAll(Tools.getUserSession().getEnterpriseId());
    }

    /** 功能描述: 查询企业角色总数
      * @param enterpriseId 企业ID
     * @return: java.lang.Integer
     * @Author: lj
     * @Date: 2019/10/25 14:51
     */
    @Override
    public Integer queryRoleCount(String enterpriseId) {
        return roleMapper.queryRoleCount(enterpriseId);
    }

    /** 功能描述: 新增角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 17:24
    */
    @Override
    public void addRole(TRole role) throws Exception {
        role.setRoleId(UUIDUtil.getUUID()); //角色ID
        role.setCreateTime(DateTime.getNow()); //创建时间
        role.setEnterpriseId(Tools.getUserSession().getEnterpriseId()); //所属企业
        role.setIsAdminRole(1); //是否企业管理员角色 0-是  1-否
        roleMapper.addRole(role);
    }

    /** 功能描述: 更新角色
     * @param role
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:26
    */
    @Override
    public void updateRole(TRole role) throws Exception {
        roleMapper.updateRole(role);
    }

    /** 功能描述: 通过角色ID删除角色
     * @param roleId 角色ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/25 16:39
    */
    @Override
    public void deleteRole(String roleId) throws Exception {
        //通过角色ID删除角色-权限
        roleRightRelationMapper.deleteRoleRightByRoleId(roleId);
        //删除角色
        roleMapper.deleteRole(roleId);
    }


    /** 功能描述: 新增管理员角色
     * @param userRoleRelation
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 17:44
    */
    @Override
    public void addUserRoleRelation(TUserRoleRelation userRoleRelation) throws Exception {
        roleMapper.addUserRoleRelation(userRoleRelation);
    }

    /** 功能描述: 通过管理员ID删除管理员角色
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:47
    */
    @Override
    public void deleteUserRoleRelationByUserId(String userId) throws Exception {
        roleMapper.deleteUserRoleRelationByUserId(userId);
    }

    /** 功能描述: 通过用户ID获取用户角色
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TUserRoleRelation>
    * @Author: lj
    * @Date: 2019/10/24 12:01
    */
    @Override
    public List<TUserRoleRelation> getUserRoleRelationByUserId(String userId) {
        return roleMapper.getUserRoleRelationByUserId(userId);
    }

    /** 功能描述: 获取管理员的 用户-角色信息
     * @param userId 管理员ID
    * @return: com.pengniao.rdpm.entity.TUserRoleRelation
    * @Author: lj
    * @Date: 2019/10/24 17:50
    */
    @Override
    public TUserRoleRelation getAdministratorUserRoleRelation(String userId) {
        return roleMapper.getAdministratorUserRoleRelation(userId);
    }

    /** 功能描述: 通过角色ID获取角色
     * @param roleId
    * @return: com.pengniao.rdpm.entity.TRole
    * @Author: lj
    * @Date: 2019/10/25 16:01
    */
    @Override
    public TRole getRoleById(String roleId) {
        return roleMapper.getRoleById(roleId);
    }


}
