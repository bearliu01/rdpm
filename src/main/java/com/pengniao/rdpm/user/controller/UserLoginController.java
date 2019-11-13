package com.pengniao.rdpm.user.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.entity.TUser;
import com.pengniao.rdpm.user.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @program: rdpm
 * @description: 用户登录-注销
 * @author: lj
 * @create: 2019-10-11 15:35
 **/
@Controller
public class UserLoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private ServiceResponse serviceResponse;


    /** 功能描述: 用户登录
     * @param response
     * @param username
     * @param password
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/17 9:08
    */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(HttpServletRequest httpServletRequest, HttpServletResponse response, String username, String password , String captchaCode) throws Exception {
        //检验验证码
        String captchaSession = (String)httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!captchaSession.equals(captchaCode)){
            serviceResponse.setCode("captchaCode-error");
            serviceResponse.setMsg("验证码错误");
            return serviceResponse;
        }else{
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 在认证提交前准备 token（令牌）
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // 执行认证登陆
            String msg = "";
            try {
                subject.login(token);
                if (subject.isAuthenticated()) {
                    subject.hasRole("");
                    TUser user = userLoginService.getUserByLoginName(username);
                    subject.getSession().setAttribute("user_session",user);
                    msg = "success";
                }
            } catch (UnknownAccountException uae) {
                token.clear();
                msg =  "登录名不正确";
            } catch (IncorrectCredentialsException ice) {
                token.clear();
                msg =  "密码不正确";
            } catch (LockedAccountException lae) {
                token.clear();
                msg =  "账户已锁定";
            } catch (ExcessiveAttemptsException eae) {
                token.clear();
                msg =  "用户名或密码错误次数过多";
            } catch (AuthenticationException ae) {
                token.clear();
                msg =  "用户名或密码不正确！";
            }
            serviceResponse.setCode("success");
            serviceResponse.setMsg(msg);
            return serviceResponse;
        }

    }

    @RequestMapping("/login/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
