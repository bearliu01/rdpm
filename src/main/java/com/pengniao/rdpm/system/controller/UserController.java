package com.pengniao.rdpm.system.controller;

import com.pengniao.rdpm.component.GridData;
import com.pengniao.rdpm.component.Pagination;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.dto.TUserDTO;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.system.mapper.UserMapper;
import com.pengniao.rdpm.system.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rdpm
 * @description: 用户
 * @author: lj
 * @create: 2019-10-28 09:45
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ServiceResponse serviceResponse;
    @Autowired
    private TUser user;

    /** 功能描述: 分页获取用户
     * @param pagination
     * @param page
     * @param rows
    * @return: com.pengniao.rdpm.component.GridData<com.pengniao.rdpm.entity.TUser>
    * @Author: lj
    * @Date: 2019/10/28 9:56
    */
    @RequestMapping("system/user/getUser")
    public GridData<TUser> getUser(Pagination pagination, Integer page, Integer rows)
    {
        List<TUser> userList= userService.getUserByEnterpriseId(pagination.getStartRow(), pagination.getRows()); //获取数据
        Integer count = userService.queryUserCountByEnterpriseId(); //获取数据总记录数
        pagination.setRecords(count);
        GridData<TUser> gridData = new GridData<TUser>(pagination);// 构建返回给前台页面使用的实体对象
        gridData.setRows(userList);
        return gridData;
    }

    /** 功能描述: 新增用户
     * @param userDTO
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/28 11:16
    */
    @RequestMapping("system/user/addUser")
    public Object addUser(TUserDTO userDTO) throws Exception{
        //判断登录账号是否已被注册
        TUser _user = userService.getUserByLoginName(userDTO.getLoginName());
        if(_user != null){
            throw new Exception("账号已被注册！");
        }
        BeanUtils.copyProperties(user,userDTO);
        userService.insertUser(user,userDTO.getRoleIds());
        serviceResponse.setCode("success");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }

    /** 功能描述: 更新用户
     * @param userDTO
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/28 16:54
    */
    @RequestMapping("system/user/updateUser")
    public Object updateUser(TUserDTO userDTO) throws Exception{
        //判断登录账号是否已被注册
        boolean registerCheck = userService.registerCheck(userDTO.getLoginName(),userDTO.getUserId());
        if(registerCheck == false){
            throw new Exception("账号已被注册！");
        }
        BeanUtils.copyProperties(user,userDTO);
        userService.updateUser(user,userDTO.getRoleIds());
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 更新用户基本信息
     * @param userDTO
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/30 16:47
    */
    @RequestMapping("system/user/updateUserBase")
    public Object updateUserBase(TUserDTO userDTO) throws Exception{
        //判断登录账号是否已被注册
        boolean registerCheck = userService.registerCheck(userDTO.getLoginName(),userDTO.getUserId());
        if(registerCheck == false){
            throw new Exception("账号已被注册！");
        }
        BeanUtils.copyProperties(user,userDTO);
        userService.updateUserBase(user);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更新成功");
        return serviceResponse;
    }

    /** 功能描述: 通过用户ID删除用户
     * @param userId 用户ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/28 17:38
    */
    @RequestMapping("system/user/deleteUserById")
    public Object deleteUserById(String userId) throws Exception{
        userService.deleteUserById(userId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("删除成功");
        return serviceResponse;
    }

    /** 功能描述: 更改用户状态
     * @param isUse 0- 启用  1-停用
     * @param userId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/29 8:59
    */
    @RequestMapping("system/user/updateUserIsUse")
    public Object updateUserIsUse(Integer isUse, String userId) throws Exception{
        userService.updateUserIsUse(isUse, userId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("更改成功");
        return serviceResponse;
    }

    /** 功能描述: 用户更新密码
     * @param oldPwd
     * @param newPwd
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/11/1 9:31
    */
    @RequestMapping("system/user/updateUserPassword")
    public Object updateUserPassword(String oldPwd, String newPwd) throws Exception{
        //判断当前用户的旧密码输入是否正确
        TUser user  = userService.getUserByUserId(Tools.getUserSession().getUserId());
        String verificationPwd = Tools.md5(user.getLoginName(),oldPwd.trim());
        if(!verificationPwd.equals(user.getPassword())){
            throw new Exception("旧密码输入错误！");
        }
        userService.updatePassword(user.getLoginName(),newPwd.trim());
        serviceResponse.setCode("success");
        serviceResponse.setMsg("密码设置成功");
        return serviceResponse;
    }

    /** 功能描述: 初始化用户密码
     * @param userId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/11/1 9:44
    */
    @RequestMapping("system/user/initUserPassword")
    public Object initUserPassword(String userId) throws Exception{
        userService.initPwd(userId);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("密码设置成功");
        return serviceResponse;
    }
}
