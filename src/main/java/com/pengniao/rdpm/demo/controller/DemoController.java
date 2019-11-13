package com.pengniao.rdpm.demo.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.demo.service.DemoService;
import com.pengniao.rdpm.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private ServiceResponse serviceResponse;




    /** 功能描述: 通过ID获取demo
     * @param id 逻辑ID
    * @return: com.pengniao.rdpm.entity.Demo
    * @Author: lj
    * @Date: 2019/9/25 10:06
    */
    @ResponseBody
    @RequestMapping("getDemoById")
    public Demo getDemoById(String id)
    {
        return demoService.getDemoById(id);
    }

    /** 功能描述: 新增demo
     * @param demo
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/9/25 10:07
    */
    @ResponseBody
    @RequestMapping("addDemo")
    public Object addDemo(Demo demo) throws Exception{
        demoService.addDemo(demo);
        serviceResponse.setCode("sucess");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 获取demo
     * @param page 当前页
     * @param rows 每页显示行数
    * @return: com.pengniao.rdpm.component.ResultMap<java.util.List<com.pengniao.rdpm.entity.Demo>>
    * @Author: lj
    * @Date: 2019/9/25 10:36
    */
    @ResponseBody
    @RequestMapping("getDemo")
    public GridData<Demo> getDemo(Pagination pagination, Integer page, Integer rows)
    {
        List<Demo> demoList= demoService.getDemo(pagination.getStartRow(), pagination.getRows()); //获取数据
        Integer count = demoService.queryDemoCount(); //获取数据总记录数
        pagination.setRecords(count);
        GridData<Demo> gridData = new GridData<Demo>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(demoList);
        return gridData;
    }
}
