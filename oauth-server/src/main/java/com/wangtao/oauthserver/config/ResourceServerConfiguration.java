package com.wangtao.oauthserver.config;

import com.wangtao.oauthserver.common.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author : wangtao
 * @date : 2018/11/1 11:06  星期四
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {

        /**
         *
         * resourceId 用于分配给可授予的clientId  不匹配的话报错  Invalid token does not contain resource id (clientTwo)
         stateless  标记以指示在这些资源上仅允许基于令牌的身份验证
         tokenStore token的存储方式
         authenticationEntryPoint  认证异常流程处理返回
         tokenExtractor  token获取方式,默认BearerTokenExtractor 从header获取token为空则从request.getParameter("access_token")
         accessDeniedHandler 鉴权失败且主叫方已要求特定的内容类型响应
         eventPublisher

         * 只能加一个resourceId,多个会被覆盖，最后一个生效
         */
        resources.resourceId(Constant.DEMO_RESOURCE_ID).stateless(true);
    }

    /**
     * authenticated()的路径需要DEMO_RESOURCE_ID才可以访问
     * <p>
     * 如果没有配置security默认的登录页面无法访问，坑了我很久。报错提示： Full authentication is required to access this resource
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/**", "/login/**", "/logout", "forward:/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
