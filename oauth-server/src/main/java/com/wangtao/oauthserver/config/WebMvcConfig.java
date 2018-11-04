package com.wangtao.oauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author : wangtao
 * @date : 2018/11/1 11:32  星期四
 * <p>
 * 错误 ： Could not resolve view with name 'forward:/oauth/confirm_access' in servlet with name 'dispatcherServlet'
 * 解决方法： https://www.cnblogs.com/gdme1320/p/9391067.html
 * 先移除@EnableWebMvc
 * 再添加 registry.viewResolver(new InternalResourceViewResolver());
 */

@Configuration
//@EnableWebMvc
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

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver());
    }
}
