package com.pengniao.rdpm.user.service.impl;

import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.system.mapper.AdministratorMapper;
import com.pengniao.rdpm.system.mapper.UserMapper;
import com.pengniao.rdpm.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: rdpm
 * @description: 用户登录
 * @author: lj
 * @create: 2019-10-25 10:37
 **/
@Transactional
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;
    
    /** 功能描述: 通过登录名获取用户账户
     * @param loginName 登录名
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/25 10:37
    */
    @Override
    public TUser getUserByLoginName(String loginName)throws Exception {
        return userMapper.getUserByLoginName(loginName);
    }
}
