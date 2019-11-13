package com.pengniao.rdpm.zutai.controller;

import com.pengniao.rdpm.entity.TagValue;
import com.pengniao.rdpm.entity.TagsTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: rdpm
 * @description:
 * @author: lj
 * @create: 2019-10-09 10:23
 **/
@Controller
public class ZutaiController {

    /** 功能描述: 调转到组态测试页面
     * @param
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2019/10/9 10:37
    */
    @RequestMapping("skipPageToZutai")
    public String skipPageToDemo(){
        return "redirect:http://192.168.0.108:8111/web/zutai/settings/run?filename=test1";
    }

    /** 功能描述:
     * @param tags 组件图元的名称  name1, name2,name3 ,....
    * @return: com.pengniao.rdpm.entity.TagTable
    * @Author: lj
    * @Date: 2019/10/9 15:50
    */
    @CrossOrigin
    @RequestMapping("zutai/getvalues")
    @ResponseBody
    public Object getTagsValues(String tags, String callback){
        System.out.println("tags="+tags);
        TagsTable tagsTable = new TagsTable();
        if(tags == null){
            tagsTable.setLogin("1");
            tagsTable.setTotal(0);
            return tagsTable;
        }

        String []tagsArray = tags.split(",");
        List<TagValue> tagValueList = new ArrayList<TagValue>();
        TagValue tagValue = null;
        for(String tagsName : tagsArray){
            tagValue = new TagValue();
            tagValue.setId(tagsName); //设置返回图元的名称
            System.out.println(Math.random());
            tagValue.setValue(Math.random());//设置返回图元的值
            tagValue.setStatus(0);
            tagValue.setAlarm(9);
            tagValue.setUnit("");
            tagValue.setDesc("");
            tagValue.setType("String");
            tagValue.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tagValueList.add(tagValue);
        }

        tagsTable.setLogin("1");
        tagsTable.setRows(tagValueList);
        tagsTable.setTotal(tagValueList.size());
        tagsTable.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return tagsTable;
    }
}
