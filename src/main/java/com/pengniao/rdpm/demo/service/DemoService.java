package com.pengniao.rdpm.demo.service;

import com.pengniao.rdpm.entity.Demo;

import java.util.List;

/** 功能描述: Demo业务层接口
 * @param null
* @return:
* @Author: lj
* @Date: 2019/9/24 10:15
*/
public interface DemoService {

    /*** 功能描述: 通过ID获取数据
     * @param id
    * @return: com.pengniao.rdpm.entity.Demo
    * @Author: lj
    * @Date: 2019/9/24 10:16
    */
    public Demo getDemoById(String id);

    /** 功能描述: 新增DEMO
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/24 11:26
    */
    public void addDemo(Demo demo)throws Exception;

    /** 功能描述: 获取DEMO
     * @param startRow 起始行
     * @param rows 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/25 10:22
    */
    public List<Demo> getDemo(Integer startRow, Integer rows);

    /** 功能描述: 查询demo总数
     * @param
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/9/25 10:29
    */
    public Integer queryDemoCount();
}
