package com.wangtao.quartztask;

import com.wangtao.quartztask.job.SchedulerJob2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/12/20 10:06  星期四
 */
@Slf4j
@RestController
public class CronController {
    @Autowired
    private ConfigRepository configRepository;

    @RequestMapping("/cron")
    public String setCronById(long id, String cron) {
        Config one = configRepository.findOne(id);
        one.setCron(cron);
        Config save = configRepository.save(one);
        return one.toString();
    }

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/jobs")
    public Object Jobs() throws SchedulerException {

        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());

        //获取所有的job集合
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

        //可以在这进行线上任务和数据库任务匹配操作，确保该进行的活动进行活动

        return jobKeys;
    }

    @RequestMapping("task2/{jobName}")
    public Object task2(@PathVariable(value = "jobName") String jobName, String className, String cronStr) throws SchedulerException, ClassNotFoundException {
        log.info("jobName = " + jobName);
        log.info("className = " + className);
        log.info("cronStr = " + cronStr);
        if (StringUtils.isEmpty(cronStr)) {
            cronStr = "0/2 * * * * ?";
        }
        Class<Job> aClass = (Class<Job>) Class.forName(className);
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
        JobDetail jobDetail = JobBuilder
                .newJob(aClass)
                .usingJobData("jobName", jobName)
                .withIdentity(jobName, "group1")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr);
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2" + jobName, "group1")
                .withSchedule(scheduleBuilder)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);

        return jobName;
    }


}
