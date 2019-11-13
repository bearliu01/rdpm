package com.pengniao.rdpm.system.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.entity.TRoleRightRelation;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.entity.TUserRoleRelation;
import com.pengniao.rdpm.system.mapper.*;
import com.pengniao.rdpm.system.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: rdpm
 * @description: 企业管理员
 * @author: lj
 * @create: 2019-10-23 09:16
 **/
@Transactional
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TRole role;
    @Autowired
    private TUserRoleRelation userRoleRelation;
    @Autowired
    private RoleRightRelationMapper roleRightRelationMapper;
    @Autowired
    private RightMapper rightMapper;


    /** 功能描述: 分页获取企业管理员
     * @param startRow 开始行
     * @param rows 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/23 9:17
    */
    @Override
    public List<TUser> getAdministrator(Integer startRow, Integer rows) {
        return administratorMapper.getAdministrator(startRow, rows);
    }

    /** 功能描述: 查询企业管理员总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/23 9:17
    */
    @Override
    public Integer queryAdministratorCount() {
        return administratorMapper.queryAdministratorCount();
    }

    /** 功能描述: 通过企业ID获取未停用的企业管理员
     * @param enterpriseId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/23 12:12
    */
    @Override
    public TUser getAdministratorByEnterpriseId(String enterpriseId) throws Exception {
        return administratorMapper.getAdministratorByEnterpriseId(enterpriseId);
    }

    /** 功能描述: 新增企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 14:55
    */
    @Override
    public void addAdministrator(TUser user) throws Exception {
        user.setUserId(UUIDUtil.getUUID()); //用户ID
        user.setPassword(Tools.md5(user.getLoginName(),"111111")); //密码
        user.setUserName(""); //用户姓名
        user.setCreateTime(DateTime.getNow()); //创建时间
        user.setIsAdmin(0); //是管理员
        user.setIsUse(0); //启用
        user.setNoEncryPassword("111111");
        administratorMapper.addAdministrator(user);

        //新增管理员角色
        role.setRoleId(UUIDUtil.getUUID());
        role.setRoleName("企业管理员");
        role.setCreateTime(DateTime.getNow());
        role.setDescription("");
        role.setEnterpriseId(user.getEnterpriseId());
        role.setIsAdminRole(0); //是否企业管理员角色 0-是  1-否
        roleMapper.addRole(role);

        //新增管理员-角色
        userRoleRelation.setTurId(UUIDUtil.getUUID());
        userRoleRelation.setUserId(user.getUserId());
        userRoleRelation.setRoleId(role.getRoleId());
        roleMapper.addUserRoleRelation(userRoleRelation);
    }

    /** 功能描述: 通过企业ID获取企业管理员
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/24 9:42
    */
    @Override
    public TUser getAdministratorById(String userId) throws Exception {
        return administratorMapper.getAdministratorById(userId);
    }

    /** 功能描述: 更新企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 10:52
    */
    @Override
    public void updateAdministrator(TUser user) throws Exception {
        administratorMapper.updateAdministrator(user);
    }

    /** 功能描述: 删除企业管理员
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:10
    */
    @Override
    public void deleteAdministratorById(String userId) throws Exception {
        //说明：将管理员的角色和管理员-角色的引用表数据全部删除后，再删除管理员

        //通过管理员ID获取管理员角色
        List<TUserRoleRelation> userRoleRelationList = roleMapper.getUserRoleRelationByUserId(userId);
        //通过管理员ID删除出角色ID
        TUserRoleRelation userRole = roleMapper.getAdministratorUserRoleRelation(userId);
        //删除管理员-角色
        roleMapper.deleteUserRoleRelationByUserId(userId);
        //通过管理员角色ID删除权限
        roleRightRelationMapper.deleteRoleRightByRoleId(userRole.getRoleId());
        //通过管理员角色ID批量删除角色
        roleMapper.batchDeleteRoleById(userRoleRelationList);
        //删除管理员
        administratorMapper.deleteAdministratorById(userId);
    }

    /** 功能描述: 更改管理员状态
     * @param isUse 0- 启用  1-停用
     * @param userId 管理员ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 14:09
    */
    @Override
    public void updateAdministratorIsUse(Integer isUse, String userId) throws Exception {
        administratorMapper.updateAdministratorIsUse(isUse,userId);
    }


}
