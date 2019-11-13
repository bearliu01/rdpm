package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 企业管理员
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/23 9:03
*/
@Mapper
public interface AdministratorMapper {

    /** 功能描述: 获取企业管理员
     * @param startRow
     * @param limit
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/23 9:04
    */
    public List<TUser> getAdministrator(@Param("startRow") Integer startRow, @Param("limit") Integer limit);

    /** 功能描述: 查询企业管理员数量
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/23 9:04
    */
    public Integer queryAdministratorCount();

    /** 功能描述: 通过企业ID获取未停用的企业管理员
     * @param enterpriseId
    * @return: java.util.List<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/23 12:02
    */
    public TUser getAdministratorByEnterpriseId(String enterpriseId)throws Exception;

    /** 功能描述: 新增企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/23 14:54
    */
    public void addAdministrator(TUser user)throws Exception;

    /** 功能描述: 通过用户ID获取企业管理员
     * @param userId
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/24 9:34
    */
    public TUser getAdministratorById(String userId)throws Exception;

    /** 功能描述: 更新企业管理员
     * @param user
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 10:49
    */
    public void updateAdministrator(TUser user)throws Exception;

    /** 功能描述: 删除企业管理员
     * @param userId 用户ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 11:08
    */
    public void deleteAdministratorById(String userId)throws Exception;

    /** 功能描述: 更改管理员状态
     * @param isUse 0- 启用  1-停用
     * @param userId 管理员ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 14:04
    */
    public void updateAdministratorIsUse(@Param("isUse")Integer isUse, @Param("userId")String userId)throws Exception;

}
