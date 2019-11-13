package com.pengniao.rdpm.user.service;

import com.pengniao.rdpm.entity.TUser;

/** 功能描述: 用户登录
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/25 10:30
*/
public interface UserLoginService {

    /** 功能描述: 通过登录名获取登录账户
     * @param loginName
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/25 10:36
    */
    public TUser getUserByLoginName(String loginName) throws Exception;
}
