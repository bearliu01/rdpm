package com.pengniao.rdpm.system.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.entity.TUserRoleRelation;
import com.pengniao.rdpm.system.mapper.RoleMapper;
import com.pengniao.rdpm.system.mapper.UserMapper;
import com.pengniao.rdpm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: rdpm
 * @description: 用户
 * @author: lj
 * @create: 2019-10-28 09:35
 **/
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TUser user;

    private TUserRoleRelation userRoleRelation;

    /** 功能描述: 获取用户
     * @param startRow 开始行
     * @param limit 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/28 9:37
    */
    @Override
    public List<TUser> getUserByEnterpriseId(Integer startRow, Integer limit) {
        return userMapper.getUser(startRow, limit, Tools.getUserSession().getEnterpriseId());
    }

    /** 功能描述: 查询用户数量
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/28 9:38
    */
    @Override
    public Integer queryUserCountByEnterpriseId() {
        return userMapper.queryUserCount(Tools.getUserSession().getEnterpriseId());
    }

    /** 功能描述: 通过登录名获取账户信息
     * @param loginName 登录名
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/28 9:38
    */
    @Override
    public TUser getUserByLoginName(String loginName)throws Exception {

        return userMapper.getUserByLoginName(loginName);
    }

    /** 功能描述: 通过用户ID获取用户
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/28 15:43
    */
    @Override
    public TUser getUserByUserId(String userId) throws Exception {
        return userMapper.getUserByUserId(userId);
    }

    /** 功能描述: 注册用户，修改用户操作，检查用户登录账号是否允许注册
     * @param loginName 登录名
     * @param userId 用户ID
    * @return: java.lang.Boolean
    * @Author: lj
    * @Date: 2019/10/28 16:52
    */
    @Override
    public Boolean registerCheck(String loginName, String userId)throws Exception {
        TUser user = userMapper.getUserByLoginName(loginName);
        if(user == null)
        {
            return true;
        }else{
            TUser user2 = userMapper.getUserByUserId(userId);
            if(user2.getLoginName().equals(loginName))
            {
                return true;
            }else{
                return false;
            }
        }
    }


    /** 功能描述: 新增用户
     * @param user 用户
     * @param rolesArray 用户角色ID数组
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 10:32
    */
    @Override
    public void insertUser(TUser user, String rolesArray) throws Exception {
        user.setUserId(UUIDUtil.getUUID()); //用户ID
        user.setEnterpriseId(Tools.getUserSession().getEnterpriseId()); //所属企业
        user.setPassword(Tools.md5(user.getLoginName(),"111111")); // 密码
        user.setCreateTime(DateTime.getNow()); //创建时间
        user.setIsAdmin(1); //是否管理员 0-是  1-否
        user.setIsUse(0); //是否使用 0-启用 1-停用
        user.setNoEncryPassword("111111"); //未加密密码
        //新增用户
        userMapper.insertUser(user);
        //批量新增用户角色
        if(rolesArray != ""){
            String []roles = rolesArray.split(",");
            List<TUserRoleRelation>  userRoleRelationList = new ArrayList<TUserRoleRelation>();
            for(int i=0;i<roles.length;i++){
                userRoleRelation = new TUserRoleRelation();
                userRoleRelation.setTurId(UUIDUtil.getUUID()); //记录标识
                userRoleRelation.setUserId(user.getUserId()); //用户ID
                userRoleRelation.setRoleId(roles[i]); //角色ID
                userRoleRelationList.add(userRoleRelation);
            }
            roleMapper.BatchAddUserRoleRelation(userRoleRelationList);
        }

    }

    /** 功能描述: 更新用户
     * @param user 用户
     * @param rolesArray 用户角色ID数组
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 16:41
    */
    @Override
    public void updateUser(TUser user, String rolesArray) throws Exception {
        //密码加密是根据用户名和明文密码一起组合加密，所以更新用户信息的同时需要重新加密
        TUser _user = userMapper.getUserByUserId(user.getUserId());
        user.setPassword(Tools.md5(user.getLoginName(),_user.getNoEncryPassword())); // 密码
        //更新用户信息
        userMapper.updateUser(user);
        //删除用户-角色
        roleMapper.deleteUserRoleRelationByUserId(user.getUserId());
        //新增用户-角色
        if(rolesArray != "") {
            String[] roles = rolesArray.split(",");
            List<TUserRoleRelation> userRoleRelationList = new ArrayList<TUserRoleRelation>();
            for (int i = 0; i < roles.length; i++) {
                userRoleRelation = new TUserRoleRelation();
                userRoleRelation.setTurId(UUIDUtil.getUUID()); //记录标识
                userRoleRelation.setUserId(user.getUserId()); //用户ID
                userRoleRelation.setRoleId(roles[i]); //角色ID
                userRoleRelationList.add(userRoleRelation);
            }
            roleMapper.BatchAddUserRoleRelation(userRoleRelationList);
        }
    }

    /** 功能描述: 更新用户基本信息
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 16:45
    */
    @Override
    public void updateUserBase(TUser user) throws Exception {
        //密码加密是根据用户名和明文密码一起组合加密，所以更新用户信息的同时需要重新加密
        TUser _user = userMapper.getUserByUserId(user.getUserId());
        user.setPassword(Tools.md5(user.getLoginName(),_user.getNoEncryPassword())); // 密码
        userMapper.updateUserBase(user);
    }

    /** 功能描述: 通过用户ID删除用户
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 17:35
    */
    @Override
    public void deleteUserById(String userId) throws Exception {
        //删除 用户-角色
        roleMapper.deleteUserRoleRelationByUserId(userId);
        //删除用户
        userMapper.deleteUserById(userId);
    }

    /** 功能描述: 更改用户状态
     * @param isUse 0- 启用  1-停用
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/29 8:57
    */
    @Override
    public void updateUserIsUse(Integer isUse, String userId) throws Exception {
        userMapper.updateUserIsUse(isUse, userId);
    }

    /** 功能描述:修改密码
     * @param loginName 登录名
     * @param newPwd 密码
    * @return: void
    * @Author: lj
    * @Date: 2019/10/31 12:07
    */
    @Override
    public void updatePassword(String loginName,String newPwd) throws Exception {
        user.setUserId(Tools.getUserSession().getUserId());
        user.setPassword(Tools.md5(loginName, newPwd));
        user.setNoEncryPassword(newPwd);
        userMapper.updatePassword(user);
    }

    /** 功能描述: 初始化密码
     * @param userId
     * @return: void
     * @Author: lj
     * @Date: 2019/11/1 9:34
     */
    @Override
    public void initPwd(String userId) throws Exception {
        //通过用户ID获取用户信息
        TUser user = userMapper.getUserByUserId(userId);
        user.setUserId(userId);
        user.setPassword(Tools.md5(user.getLoginName(), "111111"));
        user.setNoEncryPassword("111111");
        userMapper.updatePassword(user);
    }

}
