package com.pengniao.rdpm.system.service;

import com.pengniao.rdpm.entity.Demo;
import com.pengniao.rdpm.entity.Enterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 企业维护
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/18 9:42
*/
public interface EnterpriseService {

    /** 功能描述: 分页显示企业
     * @param startRow 开始行
     * @param rows 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/18 11:35
    */
    public List<Enterprise> getEnterprise(Integer startRow, Integer rows);

    /** 功能描述: 获取所有企业
     * @param 
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/23 11:17
    */
    public List<Enterprise> getEnterpriseForAll();

    /** 功能描述: 查询企业总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/18 11:36
    */
    public Integer queryEnterpriseCount();

    /** 功能描述: 新增企业
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2019/10/18 9:43
    */
    public void addEnterprise(Enterprise enterprise)throws Exception;

    /** 功能描述: 通过企业ID获取企业
     * @param enterpriseId
    * @return: com.pengniao.rdpm.entity.Enterprise
    * @Author: lj
    * @Date: 2019/10/18 14:27
    */
    public Enterprise getEnterpriseById(String enterpriseId);

    /** 功能描述: 更新企业
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/18 17:12
    */
    public void updateEnterprise(Enterprise enterprise)throws Exception;

    /** 功能描述: 更新企业基本信息
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 14:57
    */
    public void updateEnterpriseBase(Enterprise enterprise)throws Exception;

    /** 功能描述: 删除企业
     * @param enterpriseId
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 9:55
    */
    public void deleteEnterprise(String enterpriseId)throws Exception;

    /** 功能描述: 启用|停用 企业
     * @param enterpriseId 企业ID
     * @param enterpriseIsUse 0-启用  1-停用
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 10:55
    */
    public void updateIsUse(String enterpriseId,Integer enterpriseIsUse)throws Exception;

}
