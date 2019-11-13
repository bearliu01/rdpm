package com.pengniao.rdpm.system.mapper;

import com.pengniao.rdpm.entity.Demo;
import com.pengniao.rdpm.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 功能描述: 企业维护
 * @param null
* @return:
* @Author: lj
* @Date: 2019/10/18 9:23
*/
@Mapper
public interface EnterpriseMapper {

    /** 功能描述: 分页查询企业
     * @param startPage 起始页
     * @param limit 每页行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/18 11:27
    */
    public List<Enterprise> getEnterprise(@Param("startRow") Integer startRow, @Param("limit") Integer limit);

    /** 功能描述: 获取所有的企业
     * @param 
    * @return: java.util.List<com.pengniao.rdpm.entity.Enterprise>
    * @Author: lj
    * @Date: 2019/10/23 11:14
    */
    public List<Enterprise> getEnterpriseForAll();

    /** 功能描述: 查询企业总数
     * @param
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/10/18 11:28
    */
    public Integer queryEnterpriseCount();


    /** 功能描述: 新增企业
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/18 9:25
    */
    public void addEnterprise(Enterprise enterprise)throws Exception;

    /** 功能描述: 通过ID获取企业
     * @param enterpriseId 企业ID
    * @return: com.pengniao.rdpm.entity.Enterprise
    * @Author: lj
    * @Date: 2019/10/18 14:25
    */
    public Enterprise getEnterpriseById(String enterpriseId);

    /** 功能描述: 更新企业
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/18 17:09
    */
    public void updateEnterprise(Enterprise enterprise)throws Exception;

    /** 功能描述: 更新企业基本信息
     * @param enterprise
    * @return: void
    * @Author: lj
    * @Date: 2019/10/30 14:55
    */
    public void updateEnterpriseBase(Enterprise enterprise)throws Exception;

    /** 功能描述: 删除企业
     * @param enterpriseId 企业ID
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 9:54
    */
    public void deleteEnterprise(String enterpriseId)throws Exception;

    /** 功能描述: 启用|停用企业
     * @param enterpriseId
     * @param enterpriseIsUse
    * @return: void
    * @Author: lj
    * @Date: 2019/10/21 10:54
    */
    public void updateIsUse(@Param("enterpriseId")String enterpriseId, @Param("enterpriseIsUse")Integer enterpriseIsUse)throws Exception;
}
