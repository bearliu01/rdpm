package com.pengniao.rdpm.quartz.service.impl;

import com.pengniao.rdpm.entity.JobTask;
import com.pengniao.rdpm.quartz.service.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: rdpm
 * @description:
 * @author: lj
 * @create: 2019-11-05 15:03
 **/
@Transactional
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private Scheduler scheduler;

    /** 功能描述: 新增定时任务
     * @param jobTask 工作任务
     * @param jobClass 任务类
    * @return: void
    * @Author: lj
    * @Date: 2019/11/7 14:47
    */
    @Override
    public void addTask(JobTask jobTask, Class jobClass) throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobTask.getCronExpression());
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobTask.getJobName(), jobTask.getJobGroupName()).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobTask.getTriggerName(), jobTask.getTriggerGroupName())
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
