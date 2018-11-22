package com.wangtao.oauthservercode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.security.Principal;

/**
 * @author wangtao
 */
@RestController
@SpringBootApplication
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class OauthServerCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthServerCodeApplication.class, args);
    }


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration
            extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/user")
                    .authorizeRequests().anyRequest().authenticated();
        }
    }

    @Configuration
    protected static class WebMvcConfig extends WebMvcConfigurationSupport {
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

    @RequestMapping("/index")
    public String index() {
        return "this is an authorization server";
    }

    @RequestMapping({"/user", "me"})
    public Principal user(Principal principal) {
        System.out.println("principal " + principal.toString());
        return principal;
    }
}
