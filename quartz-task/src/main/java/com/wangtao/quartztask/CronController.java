//package com.wangtao.quartztask;
//
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.quartz.impl.matchers.GroupMatcher;
//import org.quartz.plugins.history.LoggingJobHistoryPlugin;
//import org.quartz.plugins.history.LoggingTriggerHistoryPlugin;
//import org.quartz.simpl.CascadingClassLoadHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author : wangtao
// * @date : 2018/12/20 10:06  星期四
// */
//@Slf4j
//@RestController
//public class CronController {
////    @Autowired
////    private ConfigRepository configRepository;
////
////    @RequestMapping("/cron")
////    public String setCronById(long id, String cron) {
////        Config one = configRepository.findOne(id);
////        one.setCron(cron);
////        Config save = configRepository.save(one);
////        return one.toString();
////    }
//
//    @Autowired
//    private Scheduler scheduler;
//
//
//    @RequestMapping("/jobs")
//    public Object Jobs(String name, String groupName) throws SchedulerException {
//
//        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
//        for (TriggerKey triggerKey : triggerKeys) {
//            System.out.println("[TriggerKey] group = " + triggerKey.getGroup() + " ,name = " + triggerKey.getName());
//            System.out.println("[TriggerKey] state = " + scheduler.getTriggerState(triggerKey));
//        }
//        //获取所有的job集合
//        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(groupName)) {
//            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
//            List a = new ArrayList<JobDetail>();
//            for (JobKey jobKey : jobKeys) {
//                System.out.println("[JobKey] group = " + jobKey.getGroup() + " ,name = " + jobKey.getName());
//                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//                a.add(jobDetail);
//            }
//            return a;
//        } else {
//            JobKey jobKey = new JobKey(name, groupName);
//            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//            return jobDetail;
//        }
//
//        //可以在这进行线上任务和数据库任务匹配操作，确保该进行的活动进行活动
//
//
//    }
//
//    @RequestMapping("task2/{jobName}")
//    public Object task2(@PathVariable(value = "jobName") String jobName, String className, String cronStr) throws SchedulerException, ClassNotFoundException {
//        log.info("jobName = " + jobName);
//        log.info("className = " + className);
//        log.info("cronStr = " + cronStr);
//        if (StringUtils.isEmpty(cronStr)) {
//            boolean validExpression = CronExpression.isValidExpression(cronStr);
//            if (validExpression) {
//                cronStr = "0/2 * * * * ?";
//            }
//        }
//        Class<Job> aClass = (Class<Job>) Class.forName(className);
//        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
//        JobDetail jobDetail = JobBuilder
//                .newJob(aClass)
//                .usingJobData("jobName", jobName)
//                .usingJobData("jobSays", "hello world!")
//                .usingJobData("myFloatValue", 3.141f)
//                .withIdentity(jobName, "group1")
//                .withDescription("my test job")
//                .build();
//        System.out.println(jobDetail.getDescription());
//
////        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr);
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(2018, 12, 24, 18, 10, 10);
//        // 每3s执行一次
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger2" + jobName, "group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule(cronStr).withMisfireHandlingInstructionFireAndProceed())
//                .startNow()
//                .endAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MINUTE))
//                .withPriority(1)
//                .withDescription("my cron trigger")
//
//                .build();
//
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//        scheduler.getListenerManager().addJobListener(new MyJobListener());
//        LoggingJobHistoryPlugin loggingJobHistoryPlugin = new LoggingJobHistoryPlugin();
//        loggingJobHistoryPlugin.initialize("myPlugin",scheduler,null );
//        scheduler.getListenerManager().addJobListener(loggingJobHistoryPlugin);
////        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
//
//        LoggingTriggerHistoryPlugin loggingTriggerHistoryPlugin = new LoggingTriggerHistoryPlugin();
//        loggingTriggerHistoryPlugin.initialize("myTriggerPlugin",scheduler,null );
//        scheduler.getListenerManager().addTriggerListener(loggingTriggerHistoryPlugin);
//        return jobName;
//    }
//
//
//}
