package com.wangtao.quartztask;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : wangtao
 * @date : 2018/12/20 9:58  星期四
 */

@Slf4j
@Configuration
public class QuartzConfigration {
    //    /**
//     *      * attention:
//     *      * Details：配置定时任务
//     *     
//     */
//    @Bean(name = "jobDetail")
//    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {// ScheduleTask为需要执行的任务
//        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
//        /*
//         *  是否并发执行
//         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
//         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
//         */
//        jobDetail.setConcurrent(false);
//
//        jobDetail.setName("srd-chhliu");// 设置任务的名字
//        jobDetail.setGroup("srd");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
//
//        /*
//         * 为需要执行的实体类对应的对象
//         */
//        jobDetail.setTargetObject(task);
//
//        /*
//         * sayHello为需要执行的方法
//         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
//         */
//        jobDetail.setTargetMethod("sayHello");
//        return jobDetail;
//    }
//
//    /**
//     *      * attention:
//     *      * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
//     *     
//     */
//    @Bean(name = "jobTrigger")
//    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
//        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
//        tigger.setJobDetail(jobDetail.getObject());
//        tigger.setCronExpression("0 30 20 * * ?");// 初始时的cron表达式
//        tigger.setName("srd-chhliu");// trigger的name
//        return tigger;
//
//    }
//
//    /**
//     *      * attention:
//     *      * Details：定义quartz调度工厂
//     *     
//     */
//    @Bean(name = "scheduler")
//    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
//        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
//        bean.setOverwriteExistingJobs(true);
//        // 延时启动，应用启动1秒后
//        bean.setStartupDelay(1);
//        // 注册触发器
//        bean.setTriggers(cronJobTrigger);
//        return bean;
//    }
    @Autowired
    DataSource dataSource;

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "myFirstExerciseJobBean")
    public JobDetailFactoryBean myFirstExerciseJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-myFirstExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(ScheduleTask.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myFirstExerciseJobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("myFirstExerciseJobBean") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-myFirstExerciseJobTrigger");
        return tigger;
    }

    /**
     * 调度器工厂Bean
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(Trigger... triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        } catch (IOException e) {
            log.error("加载quartz.properties失败", e);
            throw new Error(e);
        }
        bean.setQuartzProperties(p);

        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        // 注册触发器
        bean.setTriggers(triggers);
        bean.setDataSource(dataSource);
        return bean;
    }
}
