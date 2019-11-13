package com.pengniao.rdpm.demo.service.impl;

import com.pengniao.rdpm.component.DateTime;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.demo.mapper.DemoMapper;
import com.pengniao.rdpm.demo.service.DemoService;
import com.pengniao.rdpm.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: rdpm
 * @description: Demo接口业务实现类
 * @author: lj
 * @create: 2019-09-24 10:17
 **/
@Transactional
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    /*** 功能描述: 通过ID获取数据
     * @param id
    * @return: com.pengniao.rdpm.entity.Demo
    * @Author: lj
    * @Date: 2019/9/24 10:18
    */
    @Override
    public Demo getDemoById(String id) {
        return demoMapper.getDemoById(id);
    }

    /** 功能描述: 新增DEMO
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/24 11:27
    */
    @Override
    public void addDemo(Demo demo) throws Exception {
        demo.setId(UUIDUtil.getUUID()); //生成UUID
        demo.setCreateTime(DateTime.getNow()); //生成系统时间
        demoMapper.addDemo(demo);
    }

    /** 功能描述: 获取demo
     * @param startRow 起始行
     * @param rows 每页显示行数
    * @return: java.util.List<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/25 10:23
    */
    @Override
    public List<Demo> getDemo(Integer startRow, Integer rows) {
        return demoMapper.getDemo(startRow , rows);
    }

    /** 功能描述: 查询demo总数
     * @param
    * @return: java.lang.Integer
    * @Author: lj
    * @Date: 2019/9/25 10:30
    */
    @Override
    public Integer queryDemoCount() {
        return demoMapper.queryDemoCount();
    }
}
