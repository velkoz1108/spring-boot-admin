package com.wangtao.oauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author : wangtao
 * @date : 2018/11/1 11:32  星期四
 */

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 配置授权确认页面视图 没生效 没有授权页面
     * registry.addViewController("/oauth/confirm_access").setViewName("authorize");
     *
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

}
