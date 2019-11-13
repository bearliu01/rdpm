package com.pengniao.rdpm.demo.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.demo.dao.MongonDemoDao;
import com.pengniao.rdpm.demo.service.MongonDemoService;
import com.pengniao.rdpm.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: rdpm
 * @description: MongonDemo的业务层实现
 * @author: lj
 * @create: 2019-09-27 15:58
 **/
@Transactional
@Service
public class MongonDemoServiceImpl implements MongonDemoService {

    @Autowired
    private MongonDemoDao mongonDemoDao;

    private Query query = null;
    private Sort sort = null;
    
    /** 功能描述: 新增demo
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/27 16:07
    */
    @Override
    public void addMongonDemo(Demo demo) throws Exception {
        demo.setId(UUIDUtil.getUUID()); //生成UUID
        demo.setCreateTime(DateTime.getNow()); //生成系统时间
        mongonDemoDao.addMongonDemo(demo);
    }

    @Override
    public List<Demo> getMongoDemo(Integer page, Integer size) {
        System.out.println("page="+page);
        System.out.println("size="+size);
        query = new Query();
        sort= new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(page-1, size,sort);
        System.out.println("pageable="+pageable);
        List<Demo> demoList = mongonDemoDao.getMongoDemo(query, pageable);
        System.out.println("demoList="+demoList.size());
        return demoList;
    }

    @Override
    public Long queryMongoDemoCount() {
        Query query = new Query();
        return mongonDemoDao.queryMongoDemoCount(query);
    }

    @Override
    public void deleteMongoDemo(String id) throws Exception {
        Query query = Query.query(Criteria.where("id").is(id));
        mongonDemoDao.deleteMongoDemo(query);
    }
}
