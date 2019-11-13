package com.pengniao.rdpm.system.route;

import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRight;
import com.pengniao.rdpm.entity.TRole;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: rdpm
 * @description: 系统管理-路由
 * @author: lj
 * @create: 2019-10-17 15:28
 **/
@Controller
public class SystemRouteController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private RightService rightService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    /** 功能描述: 跳转到 企业管理 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/17 15:30
    */
    @RequestMapping("system/enterprise/skipPageToEnterprise")
    public String skipPageToEnterprise(){
        return "admin/system/enterprise";
    }

    /** 功能描述: 跳转到 企业管理-新增 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/17 16:05
    */
    @RequestMapping("system/enterprise/skipPageToAddEnterprise")
    public String skipPageToAddEnterprise(){
        return "admin/system/enterprise-add";
    }

    /** 功能描述: 跳转到 企业管理-修改 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/18 14:34
    */
    @RequestMapping("system/enterprise/skipPageToUpdateEnterprise")
    public ModelAndView  skipPageToUpdateEnterprise(String enterpriseId){
        Enterprise enterprise  = enterpriseService.getEnterpriseById(enterpriseId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/enterprise-update");
        modelAndView.addObject("enterprise", enterprise);
        return modelAndView;
    }

    /** 功能描述: 跳转到权限页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/22 9:02
    */
    @RequestMapping("system/right/skipPageToRight")
    public String skipPageToRight(){
        return "admin/system/right";
    }

    /** 功能描述: 跳转到权限-新增页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/22 11:19
    */
    @RequestMapping("system/right/skipPageToAddRight")
    public ModelAndView skipPageToAddRight(Integer rightId, String rightName)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/right-add");
        modelAndView.addObject("rightId", rightId);
        modelAndView.addObject("rightName", rightName);
        return modelAndView;
    }

    /** 功能描述: 跳转到权限-修改页面
     * @param rightId
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/22 15:08
    */
    @RequestMapping("system/right/skipPageToUpdateRight")
    public ModelAndView skipPageToUpdateRight(Integer rightId) throws Exception {
        TRight right = rightService.getRightById(rightId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/right-update");
        modelAndView.addObject("right", right);
        return modelAndView;
    }

    /** 功能描述: 跳转到 企业管理员 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/22 17:30
    */
    @RequestMapping("system/administrators/skipPageToAdministrators")
    public String skipPageToAdministrators(){
        return "admin/system/administrators";
    }

    /** 功能描述:跳转到 企业管理员 - 新增 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/23 10:45
    */
    @RequestMapping("system/administrators/skipPageToAddAdministrators")
    public ModelAndView skipPageToAddAdministrators(){
        List<Enterprise> enterpriseList = enterpriseService.getEnterpriseForAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/administrators-add");
        modelAndView.addObject("enterpriseList", enterpriseList);
        return modelAndView;
    }

    /** 功能描述: 跳转到企业管理员 - 修改 页面
     * @param
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: lj
     * @Date: 2019/10/24 9:32
     */
    @RequestMapping("system/administrators/skipPageToUpdateAdministrators")
    public ModelAndView skipPageToUpdateAdministrators(String userId) throws Exception {
        TUser administrator = administratorService.getAdministratorById(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/administrators-update");
        modelAndView.addObject("administrator", administrator);
        return modelAndView;
    }

    /** 功能描述: 跳转到管理员赋权限页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/24 14:57
    */
    @RequestMapping("system/administrators/skipPageToRightTree")
    public ModelAndView skipPageToRightTree(String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/right-tree");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

    /** 功能描述: 跳转到角色页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/25 14:08
    */
    @RequestMapping("system/role/skipPageToRole")
    public ModelAndView skipPageToRole() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/role");
        return modelAndView;
    }

    /* 功能描述: 调转到角色 - 新增 页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/25 15:07
    */
    @RequestMapping("system/role/skipPageToAddRole")
    public ModelAndView skipPageToAddRole() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/role-add");
        return modelAndView;
    }

    /** 功能描述: 跳转到 角色-更新 页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/25 15:57
    */
    @RequestMapping("system/role/skipPageToUpdateRole")
    public ModelAndView skipPageToUpdateRole(String roleId) throws Exception {
        TRole role = roleService.getRoleById(roleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.setViewName("admin/system/role-update");
        return modelAndView;
    }

    /** 功能描述: 跳转到角色 - 赋权限 页面
     * @param userId
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/25 17:35
    */
    @RequestMapping("system/role/skipPageToRoleRightTree")
    public ModelAndView skipPageToRoleRightTree(String roleId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/role-right-tree");
        modelAndView.addObject("roleId", roleId);
        return modelAndView;
    }

    /** 功能描述: 跳转到用户页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/21 17:10
    */
    @RequestMapping("system/user/skipPageToUser")
    public String skipPageToUser(){
        return "admin/system/user";
    }

    /** 功能描述: 跳转到用户 - 新增 页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/28 10:12
    */
    @RequestMapping("system/user/skipPageToAddUser")
    public ModelAndView skipPageToAddUser() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/user-add");
        return modelAndView;
    }

    /** 功能描述: 跳转到用户 - 修改页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/28 15:39
    */
    @RequestMapping("system/user/skipPageToUpdateUser")
    public ModelAndView skipPageToUpdateUser(String userId) throws Exception {
        TUser user = userService.getUserByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/system/user-update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /** 功能描述: 跳转到修改企业基本信息页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/30 15:05
    */
    @RequestMapping("system/main/skipPageToUpdateEnterpriseBase")
    public ModelAndView skipPageToUpdateEnterpriseBase() throws Exception {
        Enterprise enterprise = enterpriseService.getEnterpriseById(Tools.getUserSession().getEnterpriseId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("enterprise-base-update");
        modelAndView.addObject("enterprise", enterprise);
        return modelAndView;
    }

    /** 功能描述:  跳转到修改用户基本信息页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/31 9:18
    */
    @RequestMapping("system/main/skipPageToUpdateUserBase")
    public ModelAndView skipPageToUpdateUserBase() throws Exception {
        TUser user = userService.getUserByUserId(Tools.getUserSession().getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-base-update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /** 功能描述: 跳转到更新密码页面
     * @param 
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: lj
    * @Date: 2019/10/31 9:19
    */
    @RequestMapping("system/main/skipPageToUpdatePassword")
    public ModelAndView skipPageToUpdatePassword() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-password-update");
        return modelAndView;
    }

    /** 功能描述: 跳转到定时任务 页面
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/11/7 15:54
    */
    @RequestMapping("system/task/skipPageToTask")
    public String skipPageToTask(){
        return "admin/system/task";
    }
    
    /** 功能描述: 跳转到新增任务
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/11/7 16:33
    */
    @RequestMapping("system/task/skipPageToAddTask")
    public String skipPageToAddTask(){
        return "admin/system/task-add";
    }
}
