package com.pengniao.rdpm.user.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: rdpm
 * @description: 路由
 * @author: lj
 * @create: 2019-10-17 11:48
 **/
@Controller
public class UserRouteController {


    /** 功能描述: 跳转到登录界面 快捷键 /**  +enter
     * @param
     * @return:
     * @Author: lj
     * @Date:
     */
    @RequestMapping({"/rdpm","index.html","login.html"})
    public String index(){
        return "login";
    }

    /** 功能描述: 转场页面，到登录
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/17 11:50
    */
    @RequestMapping("/skipPageToLogin")
    public String skipPageToLogin(){
        return "toLogin";
    }

    /*+* 功能描述: 跳转到主页
     * @param
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2019/10/15 10:26
     */
    @RequestMapping("/skipPageToMain")
    public String skipPageToMain(){
        return "main";
    }


    /***功能描述: 加载欢迎页面
     * @param
     * @return:
     * @Author: lj
     * @Date:
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "admin/welcome";
    }
}
