package com.pengniao.rdpm.system.service;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 企业管理员
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/23 9:13
*/
public interface AdministratorService {

    /** 功能描述: 获取企业管理员
     * @param startRow
     * @param rows
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/23 9:14
    */
    public List<TUser> getAdministrator(Integer startRow, Integer rows);

    /** 功能描述: 查询企业管理员总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/23 9:15
    */
    public Integer queryAdministratorCount();

    /** 功能描述: 通过企业ID获取未停用的企业管理员
     * @param enterpriseId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/23 12:11
    */
    public TUser getAdministratorByEnterpriseId(String enterpriseId)throws Exception;

    /** 功能描述: 新增企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 14:09
    */
    public void addAdministrator(TUser user)throws Exception;

    /** 功能描述: 通过企业ID获取系统管理员
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/24 9:42
    */
    public TUser getAdministratorById(String userId)throws Exception;

    /** 功能描述: 更新企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 10:51
    */
    public void updateAdministrator(TUser user)throws Exception;

    /** 功能描述: 删除企业管理员
     * @param userId 用户ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:10
    */
    public void deleteAdministratorById(String userId)throws Exception;

    /** 功能描述: 更改管理员是否可用
     * @param isUse 0-启用  1-停用
     * @param userId 管理员ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 14:09
    */
    public void updateAdministratorIsUse(Integer isUse, String userId)throws Exception;
}
