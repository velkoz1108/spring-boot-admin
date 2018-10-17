package com.wangtao.adminserver.notifier;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wangtao
 * @date : 2018/10/17 17:11  星期三
 */

@Configuration
public class NotifierConfig {
    @Autowired
    InstanceRepository repository;

    @Bean
    public Notifier smsNotifier() {
        return new CustomNotifier(repository);
    }
}
