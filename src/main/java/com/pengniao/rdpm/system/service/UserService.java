package com.pengniao.rdpm.system.service;

import com.pengniao.rdpm.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 用户
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/28 9:34
*/
public interface UserService {

    /** 功能描述: 获取用户
     * @param startRow 开始行
     * @param limit 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/28 9:34
    */
    public List<TUser> getUserByEnterpriseId( Integer startRow, Integer limit);

    /** 功能描述: 查询用户数量
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/28 9:34
    */
    public Integer queryUserCountByEnterpriseId();

    /** 功能描述: 通过登录名获取账户信息
     * @param loginName 登录名
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/28 9:35
    */
    public TUser getUserByLoginName(String loginName)throws Exception;

    /** 功能描述: 通过用户ID获取用户
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/28 15:43
    */
    public TUser getUserByUserId(String userId)throws Exception;


    /** 功能描述: 注册用户，修改用户操作，检查用户登录账号是否允许注册
     * @param loginName 登录名
     * @param userId 登录ID
    * @return: java.lang.Boolean
    * @Author: lj
    * @Date: 2019/10/28 16:51
    */
    public Boolean registerCheck(String loginName, String userId)throws Exception;

    /** 功能描述: 新增用户
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/28 10:31
    */
    public void insertUser(TUser user, String rolesArray)throws Exception;

    /** 功能描述: 更新用户
     * @param user
     * @param rolesArray
    * @return: void
    * @Author: lj
    * @Date: 2019/10/28 16:41
    */
    public void updateUser(TUser user, String rolesArray)throws Exception;

    /** 功能描述: 更新用户基本信息
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 16:44
    */
    public void updateUserBase(TUser user)throws Exception;

    /** 功能描述: 通过用户ID删除用户
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/28 17:33
    */
    public void deleteUserById(String userId)throws Exception;

    /** 功能描述: 更改用户状态
     * @param isUse 0- 启用  1-停用
     * @param userId 管理员ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/29 8:56
    */
    public void updateUserIsUse(Integer isUse, String userId)throws Exception;

    /** 功能描述:修改密码
     * @param loginName 登录名
     * @param newPwd 密码
     * @return: void
     * @Author: lj
     * @Date: 2019/10/31 12:07
     */
    public void updatePassword(String loginName, String newPwd)throws Exception;

    /** 功能描述: 初始化密码
     * @param userId
     * @return: void
     * @Author: lj
     * @Date: 2019/11/1 9:34
     */
    public void initPwd(String userId)throws Exception;
}
