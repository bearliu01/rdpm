package com.pengniao.rdpm.demo.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: rdpm
 * @description: 路由
 * @author: lj
 * @create: 2019-10-17 11:45
 **/
@Controller
public class RouteController {



    /** 功能描述: 跳转到demo页面
     * @param
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2019/9/23 17:49
     */
    @RequestMapping("skipPageToDemo")
    public String skipPageToDemo(){
        return "admin/system/demo";
    }

    /** 功能描述: 跳转到新增Demo 页面
     * @param
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2019/9/25 10:06
     */
    @RequestMapping("skipPageToAddDemo")
    public String skipPageToAddDemo(){
        return "admin/system/add-demo";
    }


    /** 功能描述: 跳转到mongdb-demo
     * @param
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2019/9/29 10:10
     */
    @RequestMapping("skipPageToMongoDBDEMO")
    public String skipPageToMongoDBDEMO(){
        return "admin/system/mongodb-demo";
    }

    /** 功能描述: 跳转到新增demo
     * @param
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2019/9/29 10:46
     */
    @RequestMapping("skipPageToAddMongoDbDEMO")
    public String skipPageToAddMongoDbDEMO(){
        return "admin/system/add-mongodb-demo";
    }

}
