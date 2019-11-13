package com.pengniao.rdpm.exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: rdpm
 * @description: mysql异常提示
 * @author: lj
 * @create: 2019-11-01 14:19
 **/
@Component
public class SQLExceptionPrompt {

    private Map<String,String> map = new HashMap<String,String>();

    public SQLExceptionPrompt(){
        map.put("`rdpm`.`tuserrolerelation`","用户信息正在使用数据");
        map.put("`rdpm`.`tuser`","用户信息正在使用数据");
    }

    public Map getMap() {
        return map;
    }

}
