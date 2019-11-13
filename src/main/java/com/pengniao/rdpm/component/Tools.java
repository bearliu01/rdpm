package com.pengniao.rdpm.component;

import com.pengniao.rdpm.entity.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * @program: rdpm
 * @description: 自定义工具类
 * @author: lj
 * @create: 2019-10-23 14:19
 **/
public class Tools {

    /** 功能描述: 生成4位随机数
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/23 14:20
    */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom ;
        }
        return fourRandom;
    }

    public static final String md5(String username, String pwd){
//        //加密方式
//        String hashAlgorithmName = "MD5";
//        //盐：为了即使相同的密码不同的盐加密后的结果也不同
//        ByteSource byteSalt = ByteSource.Util.bytes(salt);
//        //密码
////        Object source = password;
//        //加密次数
//        int hashIterations = 2;
//        SimpleHash result = new SimpleHash(hashAlgorithmName, password, byteSalt, hashIterations);
//        return result.toString();
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "pnkj"), 2).toHex();
        return md5Pwd;
    }

    /** 功能描述: 获取用户session
     * @param 
    * @return: com.pengniao.rdpm.entity.TUser
    * @Author: lj
    * @Date: 2019/10/25 14:55
    */
    public static TUser getUserSession(){
        TUser user = (TUser) SecurityUtils.getSubject().getSession().getAttribute("user_session");//获取用户session
        return user;
    }

    public static void main(String args[]) {
        System.out.println(md5("hnjz4115","111111"));
    }
}
