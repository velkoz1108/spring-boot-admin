package com.wangtao.quartztask;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * @author : wangtao
 * @date : 2018/12/27 11:12  星期四
 */

@Slf4j
public class DemoWithMain {
    public static void main(String[] args) {

        try {
            // 获取调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 开启调度器
            scheduler.start();

            // 注册一个示例任务和触发器
            registerJobAndTrigger(scheduler);

            // scheduler.shutdown();

        } catch (SchedulerException se) {
            log.error("调度器初始化异常", se);
        }
    }

    /**
     * 注册一个任务和触发器
     */
    public static void registerJobAndTrigger(Scheduler scheduler) {
        JobDetail job = JobBuilder.newJob(MySimpleJob.class)
                .withIdentity("mySimpleJob", "simpleGroup")
                .build();

        Trigger trigger = org.quartz.TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "simpleGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("注册任务和触发器失败", e);
        }
    }

    /**
     * 简单的任务
     */
    public static class MySimpleJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            log.info("哇真的执行了");
        }
    }
}
