package com.pengniao.rdpm.demo.service;

import com.pengniao.rdpm.entity.Demo;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/** 功能描述: MongonDemo业务层接口
 * @param null
 * @return:
 * @Author: lj
 * @Date: 2019/9/24 10:15
 */
public interface MongonDemoService {
    /** 功能描述: 新增
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/27 15:57
    */
    public void addMongonDemo(Demo demo)throws Exception;

    /** 功能描述:
     * @param startRow 获取tMongoDemo
     * @param rows
    * @return: java.util.List<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/29 17:47
    */
    public List<Demo> getMongoDemo(Integer startRow, Integer rows);

    /** 功能描述: 查询MongoDemo结果集总数
     * @param 
    * @return: java.lang.Long
    * @Author: lj
    * @Date: 2019/9/29 17:48
    */
    public Long queryMongoDemoCount();

    /** 功能描述: 删除MongoDemo
     * @param id
    * @return: void
    * @Author: lj
    * @Date: 2019/9/29 17:48
    */
    public void deleteMongoDemo(String id) throws Exception;
}
