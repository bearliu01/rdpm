package com.pengniao.rdpm.component;

import java.util.UUID;

/**
 * @program: rdpm
 * @description: 自动产生UUID
 * @author: lj
 * @create: 2019-09-24 11:32
 **/
public class UUIDUtil {

    /** 功能描述: 生成UUID
     * @param 
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/9/24 11:32
    */
    public static String getUUID()
    {
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }
}
