package com.wangtao.quartztask;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author : wangtao
 * @date : 2018/12/20 9:56  星期四
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduleTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.sayHello();
    }

    public void sayHello() {
        log.info("Hello world, i'm the king of the world!!!");
    }

}
