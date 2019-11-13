package com.pengniao.rdpm.component;

import com.pengniao.rdpm.config.WebMvcConfiguration;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: rdpm
 * @description: 请求是否登录（表单数据提交过滤器）
 * @author: lj
 * @create: 2019-10-15 16:29
 **/
public class LoginFormFilter extends FormAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            //如果是Ajax请求，不跳转登录
            if (HttpUtils.isAjaxRequest(httpServletRequest)){
                System.out.println("ajax");
                httpServletResponse.setStatus(401);
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }


}
