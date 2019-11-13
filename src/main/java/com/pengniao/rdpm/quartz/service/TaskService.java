package com.pengniao.rdpm.quartz.service;

import com.pengniao.rdpm.entity.JobTask;
import org.quartz.SchedulerException;

/** 功能描述:
 * @param null
* @return:
* @Author: lj
* @Date: 2019/11/5 15:02
*/
public interface TaskService {

    /** 功能描述: 新增任务
     * @param jobTask
     * @param jobClass
    * @return: void
    * @Author: lj
    * @Date: 2019/11/7 14:36
    */
    public void addTask(JobTask jobTask, Class jobClass) throws SchedulerException;
}
