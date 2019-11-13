package com.pengniao.rdpm.job.impl;

import com.pengniao.rdpm.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @program: rdpm
 * @description: 测试类
 * @author: lj
 * @create: 2019-11-06 11:20
 **/
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        System.out.println("Hello Job执行时间: " + new Date());
    }
}
