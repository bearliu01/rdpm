package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 功能描述: 权限
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/22 9:31
*/
@Mapper
public interface RightMapper {

    /** 功能描述: 获取权限
     * @param 
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/22 9:33
    */
    public List<TRight> getRight();

    /** 功能描述: 通过权限ID获取权限
     * @param rightId
    * @return: com.pengniao.rdpm.entity.TRight
    * @Author: lj
    * @Date: 2019/10/22 9:34
    */
    public TRight getRightById(Integer rightId)throws Exception;

    /** 功能描述: 通过权限ID获取子节点
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/22 15:57
    */
    public List<TRight> getChildRightById(Integer rightId);

    /** 功能描述: 新增权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 14:21
    */
    public void addRight(TRight right)throws Exception;

    /** 功能描述: 获取最后一个权限ID
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/22 14:29
    */
    public Integer getLastRightId()throws Exception;

    /** 功能描述: 修改权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 15:26
    */
    public void updateRight(TRight right)throws Exception;

    /** 功能描述: 删除权限
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/22 15:39
    */
    public void deleteRight(Integer rightId)throws Exception;

    /** 功能描述: 通过用户ID获取权限
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:24
    */
    public List<TRight> getRightByUserId(String userId);

    /** 功能描述: 通过登录账号获取角色
     * @param loginName 登录账号
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:56
    */
    public List<TRight> getRightByLoginName(String loginName);
}
