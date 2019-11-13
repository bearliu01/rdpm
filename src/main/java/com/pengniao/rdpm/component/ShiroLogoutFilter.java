package com.pengniao.rdpm.component;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @program: rdpm
 * @description: shiro退出登录过滤器
 * @author: lj
 * @create: 2019-10-16 10:39
 **/
public class ShiroLogoutFilter extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request,response);
        //登出
        subject.logout();
        //获取登出后重定向到的地址
        String redirectUrl = getRedirectUrl(request,response,subject);
        //重定向
        issueRedirect(request,response,redirectUrl);
        return false;
    }
}
