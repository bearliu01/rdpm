package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 用户
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/25 10:47
*/
@Mapper
public interface UserMapper {


    /** 功能描述: 获取用户
     * @param startRow 开始行
     * @param limit 每页显示行数
     * @param enterpriseId 企业ID
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/28 9:29
    */
    public List<TUser> getUser(@Param("startRow") Integer startRow, @Param("limit") Integer limit, @Param("enterpriseId")String enterpriseId);

    /** 功能描述: 查询用户数量
     * @param enterpriseId 企业ID
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/28 9:29
    */
    public Integer queryUserCount(String enterpriseId);

    /** 功能描述: 通过登录名获取账户信息
     * @param loginName
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/25 10:52
    */
    public TUser getUserByLoginName(String loginName)throws Exception;

    /** 功能描述: 通过用户ID获取用户
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/28 15:42
    */
    public TUser getUserByUserId(String userId)throws Exception;

    /** 功能描述: 新增用户
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 10:19
    */
    public void insertUser(TUser user)throws Exception;

    /** 功能描述: 更新用户
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 16:37
    */
    public void updateUser(TUser user)throws Exception;

    /** 功能描述: 更新用户基本信息
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 15:54
    */
    public void updateUserBase(TUser user)throws Exception;

    /** 功能描述: 通过用户ID删除用户
     * @param userId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 17:35
    */
    public void deleteUserById(String userId)throws Exception;

    /** 功能描述: 更改用户状态
     * @param isUse 0- 启用  1-停用
     * @param userId 管理员ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/29 8:53
    */
    public void updateUserIsUse(@Param("isUse")Integer isUse, @Param("userId")String userId)throws Exception;

    /** 功能描述: 修改密码
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/31 11:59
    */
    public void updatePassword(TUser user)throws Exception;


}
