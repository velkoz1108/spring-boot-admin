//package com.wangtao.quartztask.job;
//
//import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import java.util.Date;
//
///**
// * @author : wangtao
// * @date : 2018/12/20 16:10  星期四
// */
//
//
//public class SchedulerJob2 implements Job {
//    private String jobSays;
//    private float myFloatValue;
//    private String jobName;
//
//    public void setJobSays(String jobSays) {
//        this.jobSays = jobSays;
//    }
//
//    public void setMyFloatValue(float myFloatValue) {
//        this.myFloatValue = myFloatValue;
//    }
//
//    public void setJobName(String jobName) {
//        this.jobName = jobName;
//    }
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        //这里可以获取控制器绑定的值，实际应用中可以设置为某个活动的id,以便进行数据库操作
////        Object jobName = context.getJobDetail().getKey();
////        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//
////        String jobSays = dataMap.getString("jobSays");
////        float myFloatValue = dataMap.getFloat("myFloatValue");
////        String jobName2 = dataMap.getString("jobName");
//
//        System.out.println("jobSays = " + jobSays + " ,myFloatValue = " + myFloatValue + ",jobName2 = " + jobName);
//
//        System.err.println("这是" + jobName + "任务" + new Date());
//
//    }
//}
