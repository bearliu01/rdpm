package com.pengniao.rdpm.component;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @program: rdpm
 * @description: session监听配置
 * @author: lj
 * @create: 2019-10-15 12:29
 **/
public class ShiroSessionListener implements SessionListener {
    
    /** 功能描述: 会话创建是触发
     * @param session
    * @return: void
    * @Author: lj
    * @Date: 2019/10/15 15:20
    */
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建:"+session.getId());
    }

    /** 功能描述: 会话停止时触发
     * @param session
    * @return: void
    * @Author: lj
    * @Date: 2019/10/15 15:20
    */
    @Override
    public void onStop(Session session) {
        System.out.println("会话停止:"+session.getId());
    }

    /** 功能描述: 会话过期时触发
     * @param session
    * @return: void
    * @Author: lj
    * @Date: 2019/10/15 15:19
    */
    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期:"+session.getId());
    }
}
