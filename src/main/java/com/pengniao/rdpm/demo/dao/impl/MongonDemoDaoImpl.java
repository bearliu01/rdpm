package com.pengniao.rdpm.demo.dao.impl;

import com.pengniao.rdpm.demo.dao.MongonDemoDao;
import com.pengniao.rdpm.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: rdpm
 * @description: MongonDemo数据访问层
 * @author: lj
 * @create: 2019-09-27 16:03
 **/

@Component
public class MongonDemoDaoImpl implements MongonDemoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /** 功能描述: 新增demo
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/27 16:06
    */
    public void addMongonDemo(Demo demo) throws Exception{
        this.mongoTemplate.save(demo);
    }

    /** 功能描述: 获取MongoDemo
     * @param query
     * @param pageable
    * @return: java.util.List<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/29 17:34
    */
    @Override
    public List<Demo> getMongoDemo(Query query,Pageable pageable) {
        List<Demo> demos = this.mongoTemplate.find(query.with(pageable), Demo.class);
        return demos;
    }

    /** 功能描述: 查询MongoDemo结果集总数
     * @param query
    * @return: java.lang.Long
    * @Author: lj
    * @Date: 2019/9/29 17:35
    */
    @Override
    public Long queryMongoDemoCount(Query query) {
        return this.mongoTemplate.count(query, Demo.class);
    }

    /** 功能描述: 删除MongoDemo
     * @param query
    * @return:
    * @Author: lj
    * @Date: 2019/9/29 17:35
    */
    @Override
    public void deleteMongoDemo(Query query) throws Exception {
        this.mongoTemplate.remove(query, Demo.class);
    }

}
