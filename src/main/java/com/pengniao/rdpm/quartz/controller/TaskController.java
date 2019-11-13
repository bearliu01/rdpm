package com.pengniao.rdpm.quartz.controller;

import com.pengniao.rdpm.component.ServiceResponse;
import com.pengniao.rdpm.entity.JobTask;
import com.pengniao.rdpm.quartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rdpm
 * @description:
 * @author: lj
 * @create: 2019-11-05 15:25
 **/
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ServiceResponse serviceResponse;

    /** 功能描述: 新增定时任务
     * @param jobTask 定时任务
     * @param jobClass 任务类
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/11/7 15:15
    */
    @RequestMapping("quartz/timingtask/addTask")
    public Object addTask(JobTask jobTask, Class jobClass) throws Exception{
        taskService.addTask(jobTask,jobClass);
        serviceResponse.setCode("success");
        serviceResponse.setMsg("新增成功");
        return serviceResponse;
    }
}
