package com.pengniao.rdpm.component;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * @program: rdpm
 * @description:
 * @author: lj
 * @create: 2019-11-05 14:37
 **/
@Component
public class QuartzJobFactory extends SpringBeanJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
