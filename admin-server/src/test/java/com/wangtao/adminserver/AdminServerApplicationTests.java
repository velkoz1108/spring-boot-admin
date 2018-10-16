package com.wangtao.adminserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean

    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数

    @Value("${spring.boot.admin.notify.mail.to}")
    private String to; //读取配置文件中的参数


    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

}
