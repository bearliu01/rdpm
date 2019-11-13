package com.pengniao.rdpm.realm;

import com.pengniao.rdpm.entity.Enterprise;
import com.pengniao.rdpm.entity.TRight;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.system.service.AdministratorService;
import com.pengniao.rdpm.system.service.EnterpriseService;
import com.pengniao.rdpm.system.service.RightService;
import com.pengniao.rdpm.user.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: rdpm
 * @description: ShiroRealm
 * @author: lj
 * @create: 2019-10-11 15:17
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RightService rightService;
    @Autowired
    private EnterpriseService enterpriseService;

    /** 功能描述: 用户权限验证
     * @param principalCollection
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: lj
    * @Date: 2019/10/11 16:16
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------权限认证方法--------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<TRight> rightList = rightService.getRightByLoginName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<String>();
        for(TRight right: rightList)
        {
            stringSet.add(right.getAction());
        }
//        stringSet.add("user:show");
//        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    /** 功能描述: 用户登录验证
     * @param authenticationToken
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: lj
    * @Date: 2019/10/11 16:16
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据登录名从数据库获取密码
        TUser user = null;
        try {
            user = userLoginService.getUserByLoginName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String password = "45971d40f1782f7454b912c26fed5ad6"; //123加密后

        if (user == null) {
            throw new UnknownAccountException("登录名不正确");
        }
        if(user.getEnterprise().getEnterpriseIsUse() == 1 ||user.getIsUse() == 1){
            //企业被停用 或 用户被停用 ，将禁止用户登录
            throw new LockedAccountException("账户已锁定");
        }
//        else if (!userPwd.equals(user.getPassword() )) {
//            throw new IncorrectCredentialsException("密码不正确");
//        }
//        return new SimpleAuthenticationInfo(userName, password,getName());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(userName, user.getPassword(), ByteSource.Util.bytes(userName + "pnkj"), getName());
    }
}
