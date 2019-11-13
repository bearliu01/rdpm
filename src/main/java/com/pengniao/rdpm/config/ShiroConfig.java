package com.pengniao.rdpm.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.pengniao.rdpm.component.LoginFormFilter;
import com.pengniao.rdpm.component.ShiroLogoutFilter;
import com.pengniao.rdpm.component.ShiroSessionListener;
import com.pengniao.rdpm.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.SessionListenerAdapter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.*;

/**
 * @program: rdpm
 * @description: shiro配置
 * @author: lj
 * @create: 2019-10-11 15:14
 **/
@Configuration
public class ShiroConfig extends SessionListenerAdapter {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/skipPageToLogin"); //默认登录的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login/getKaptchaImage", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/toastr/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");//logout是shiro提供的过滤器,这是走自定义的 shiroLogoutFilter 上面有配置
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map<String, Filter> filters = new HashMap<String,Filter>();
        Filter loginFilter = new LoginFormFilter(); //登录过滤器
        filters.put("authc", loginFilter); //此处使用自定义的拦截器,autho默认使用FormAuthenticationFilter拦截器

        filters.put("logout", shiroLogoutFilter());//配置自定义登出 覆盖 logout 之前默认的LogoutFilter
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }


    /** 功能描述: 退出登录Filter
     * @param 
    * @return: com.pengniao.rdpm.component.ShiroLogoutFilter
    * @Author: lj
    * @Date: 2019/10/16 10:42
    */
    public ShiroLogoutFilter shiroLogoutFilter(){
        ShiroLogoutFilter shiroLogoutFilter = new ShiroLogoutFilter();
        //配置登出后重定向的地址，等出后配置跳转到登录接口
        shiroLogoutFilter.setRedirectUrl("/skipPageToLogin");
        return shiroLogoutFilter;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间3600s
        sessionManager.setGlobalSessionTimeout(36000000L);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        sessionManager.setSessionValidationInterval(3600000);
        //取消url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

    @Bean("sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        defaultSecurityManager.setSessionManager(sessionManager());
        return defaultSecurityManager;
    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        // 使用credentialsMatcher加密算法类来验证密文
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        customRealm.setCachingEnabled(false);
        return customRealm;
    }

    //thymeleaf shiro支持
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
