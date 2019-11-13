package com.pengniao.rdpm.demo.mapper;

import com.pengniao.rdpm.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: rdpm
 * @description: demo
 * @author: lj
 * @create: 2019-09-24 09:55
 **/
@Mapper
public interface DemoMapper {

    /*** 功能描述: 通过ID查询
     * @param id
    * @return: com.pengniao.rdpm.entity.Demo
    * @Author: lj
    * @Date: 2019/9/24 9:57
    */
    public Demo getDemoById(String id);
    
    /** 功能描述: 新增Demo
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/24 11:19
    */
    public void addDemo(Demo demo)throws Exception;

    /** 功能描述:
     * @param startPage 起始页
     * @param limit 每页行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/25 10:17
    */
    public List<Demo> getDemo(@Param("startPage") Integer startPage, @Param("limit") Integer limit);

    /** 功能描述: 查询demo总数
     * @param 
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/9/25 10:28
    */
    public Integer queryDemoCount();
}
