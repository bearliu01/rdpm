package com.pengniao.rdpm.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @program: rdpm
 * @description: 任务调度基础接口
 * @author: lj
 * @create: 2019-11-06 10:53
 **/
public interface BaseJob extends Job {
    public void execute(JobExecutionContext context) throws JobExecutionException;
}
