package com.pengniao.rdpm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rdpm
 * @description: 数据源Druid配置类
 * @author: lj
 * @create: 2019-09-24 09:15
 **/
@Configuration
public class DruidConfiguration {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /*** 功能描述: 配置Druid监控
     * @param 
    * @return: org.springframework.boot.web.servlet.ServletRegistrationBean
    * @Author: lj
    * @Date: 2019/9/24 9:18
    */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        //servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        servletRegistrationBean.addInitParameter("allow", ""); //默认允许所有
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        //servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
    
    /** 功能描述: 配置web监控的Filter
     * @param 
    * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
    * @Author: lj
    * @Date: 2019/9/24 9:23
    */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        System.out.println("");
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
}
