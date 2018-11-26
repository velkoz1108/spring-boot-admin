package com.wangtao.oauthserver.config;

import com.wangtao.oauthserver.common.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author : wangtao
 * @date : 2018/11/1 11:06  星期四
 * <p>
 * <p>
 * ResourceServerConfiguration 和 SecurityConfiguration上配置的顺序,  
 * SecurityConfiguration一定要在ResourceServerConfiguration 之前，
 * 因为spring实现安全是通过添加过滤器(Filter)来实现的，基本的安全过滤应该在oauth过滤之前,
 * 所以在SecurityConfiguration设置@Order(2), 在ResourceServerConfiguration上设置@Order(6)
 */
//@Order(6)
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
        resources.resourceId(Constant.RESOURCE_ORDER);
    }

    /**
     * 通过Web登录访问 http://localhost:8088/write/1
     * 返回错误：
     * Full authentication is required to access this resource
     * <p>
     * /read/1 在ResourceServerConfiguration中配置了，需通过token访问
     * 如：
     * http://localhost:8088/write/1?access_token=79abddb6-d3bb-4937-8eac-f93dc336f325
     * <p>
     * <p>
     * 授权是只给了客户端write的scope，访问http://localhost:8088/read/1 时
     * 返回范围不足的错误
     * {
     * "error": "insufficient_scope",
     * "error_description": "Insufficient scope for this resource",
     * "scope": "read"
     * }
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/user**", "/read/**", "/write/**")
                .and()
                .authorizeRequests().antMatchers("/read/**")
                .access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
                .antMatchers("/write/**")
                .access("#oauth2.hasScope('write') ")

        ;
    }
}
