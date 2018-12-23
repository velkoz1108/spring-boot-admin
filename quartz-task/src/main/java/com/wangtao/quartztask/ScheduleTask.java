package com.wangtao.quartztask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author : wangtao
 * @date : 2018/12/20 9:56  星期四
 */
@Slf4j
@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ScheduleTask {
    public void sayHello(){
        log.info("Hello world, i'm the king of the world!!!");
    }

}
