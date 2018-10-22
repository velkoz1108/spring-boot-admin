package com.wangtao.apphello.feign;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * @author : wangtao
 * @date : 2018/10/21 10:07  星期日
 *
 * BasicAuthRequestInterceptor.apply方法   template.header("Authorization", headerValue);
 * 将信息放入请求头中
 *
 * 当访问的server需要密码时，可以在此处配置
 */
@Slf4j
@Configuration
public class FeignConfig {
    @Value("${auth.client.username:admin}")
    private String username;
    @Value("${auth.client.password:admin123}")
    private String password;


    @Bean
    RequestInterceptor BasicAuthRequestInterceptor() {
        log.info("this client username is {} and password is {} .", username, password);
        return new BasicAuthRequestInterceptor(username,password,Charset.defaultCharset() );
    }
}
