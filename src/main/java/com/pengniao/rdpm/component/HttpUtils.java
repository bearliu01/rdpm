package com.pengniao.rdpm.component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: rdpm
 * @description:
 * @author: lj
 * @create: 2019-10-15 16:36
 **/
public class HttpUtils {

    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;
        return isAjax;
    }
}
