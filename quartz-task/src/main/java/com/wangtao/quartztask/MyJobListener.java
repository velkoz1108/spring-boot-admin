//package com.wangtao.quartztask;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.listeners.JobListenerSupport;
//
//import java.util.Objects;
//
///**
// * @author : wangtao
// * @date : 2018/12/25 15:44  星期二
// */
//
//
//public class MyJobListener extends JobListenerSupport {
//    @Override
//    public String getName() {
//        return "myJobListener";
//    }
//
//    @Override
//    public void jobToBeExecuted(JobExecutionContext context) {
//        System.out.println("jobToBeExecuted --> " + context.getNextFireTime());
//    }
//
//    @Override
//    public void jobExecutionVetoed(JobExecutionContext context) {
//        System.out.println("jobExecutionVetoed --> " + context.getNextFireTime());
//    }
//
//    @Override
//    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
//        System.out.println("jobWasExecuted --> " + context.getNextFireTime());
//        if (!Objects.isNull(jobException)) {
//            System.err.println("jobToBeExecuted --> " + jobException.getMessage());
//        }
//    }
//}
