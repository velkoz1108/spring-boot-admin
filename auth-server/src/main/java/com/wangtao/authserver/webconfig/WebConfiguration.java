package com.wangtao.authserver.webconfig;

import com.wangtao.authserver.handler.FeignReuqestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : wangtao
 * @date : 2018/10/21 15:37  星期日
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(feignReuqestHandler());
    }

    @Bean
    FeignReuqestHandler feignReuqestHandler() {
        return new FeignReuqestHandler();
    }
}
