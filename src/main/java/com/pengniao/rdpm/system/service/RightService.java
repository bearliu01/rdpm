package com.pengniao.rdpm.system.service;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRight;

import java.util.List;

/** 功能描述: 权限
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/22 9:45
*/
public interface RightService {
    /** 功能描述: 获取权限
     * @param 
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/22 9:45
    */
    public Object getRight() throws Exception;

    /** 功能描述: 新增权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 14:26
    */
    public void addRight(TRight right)throws Exception;

    /** 功能描述: 通过权限ID获取权限
     * @param rightId
    * @return: com.pengniao.rdpm.entity.TRight
    * @Author: lj
    * @Date: 2019/10/22 15:10
    */
    public TRight getRightById(Integer rightId)throws Exception;

    /** 功能描述: 修改权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 15:29
    */
    public void updateRight(TRight right)throws Exception;

    /** 功能描述: 删除权限
     * @param rightId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 15:44
    */
    public void deleteRight(Integer rightId)throws Exception;

    /** 功能描述: 通过权限ID获取子节点
     * @param rightId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/22 15:59
    */
    public List<TRight> getChildRightById(Integer rightId);

    /** 功能描述: 通过角色获取权限
     * @param roleId 角色ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 15:19
    */
    public Object getRight(String roleId);

    /** 功能描述: 通过管理员ID获取管理员现有的权限
     * @param userId 管理员ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 9:02
    */
    public Object getAdministratorRight(String userId);

    /** 功能描述: 新增管理员的权限
     * @param userId 管理员ID
     * @param rightStr 权限
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 17:53
    */
    public void insertAdministratorRoleRight(String userId,String rightStr)throws Exception;


    /** 功能描述: 新增角色权限
     * @param roleId 角色ID
     * @param rightStr 权限
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 16:37
    */
    public void insertRoleRight(String roleId,String rightStr)throws Exception;

    /** 功能描述: 通过用户ID获取权限
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:26
    */
    public List<TRight> getRightByUserId(String userId);

    /** 功能描述: 通过登录账号获取角色
     * @param loginName 登录账号
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:57
    */
    public List<TRight> getRightByLoginName(String loginName);
}
