package com.pengniao.rdpm.demo.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.demo.service.MongonDemoService;
import com.pengniao.rdpm.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: rdpm
 * @description: MongonDB数据CURD
 * @author: lj
 * @create: 2019-09-27 15:50
 **/
@Controller
public class MongonDemoController {

    @Autowired
    private MongonDemoService mongonDemoService;
    @Autowired
    private ServiceResponse serviceResponse;


    /** 功能描述: 新增mongo-demo
     * @param demo
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/9/27 16:17
    */
    @ResponseBody
    @RequestMapping("addMongonDemo")
    public Object addMongonDemo(Demo demo) throws Exception{
        mongonDemoService.addMongonDemo(demo);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 获取mongo-demo
     * @param pagination
     * @param page
     * @param rows
    * @return: com.pengniao.rdpm.component.GridData<com.pengniao.rdpm.entity.Demo>
    * @Author: lj
    * @Date: 2019/9/29 15:17
    */
    @ResponseBody
    @RequestMapping("getMongoDemo")
    public GridData<Demo> getMongoDemo(Pagination pagination, Integer page, Integer rows)
    {
        List<Demo> demoList= mongonDemoService.getMongoDemo(page,rows);
        Long count = mongonDemoService.queryMongoDemoCount(); //获取数据总记录数
        pagination.setRecords(Integer.parseInt(String.valueOf(count)));
        GridData<Demo> gridData = new GridData<Demo>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(demoList);
        return gridData;
    }

    /** 功能描述: 删除mongo-demo
     * @param id
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/9/29 15:17
    */
    @ResponseBody
    @RequestMapping("deleteMongonDemo")
    public Object deleteMongonDemo(String id) throws Exception{
        mongonDemoService.deleteMongoDemo(id);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }
}
