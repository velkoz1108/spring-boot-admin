package com.wangtao.authserver.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : wangtao
 * @date : 2018/10/22 9:59  星期一
 */
// 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
@EnableOAuth2Sso
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        super.configure(http);

        http.
                antMatcher("/**")
                // 所有请求都得经过认证和授权
                .authorizeRequests().anyRequest().authenticated()
                .and().authorizeRequests().antMatchers("/", "/login**","/anon").permitAll()
                .and()
                // 这里之所以要禁用csrf，是为了方便。
                // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
                // 那样我还得写一个界面，发送post请求
                .csrf().disable()
                // 退出的URL是/logout
                .logout().logoutUrl("/logout").permitAll()
                // 退出成功后，跳转到/路径。
                .logoutSuccessUrl("/");


//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();

        // @formatter:off
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl("/");
//
//        http.authorizeRequests()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").successHandler(successHandler).and()
//                .logout().logoutUrl("/logout").and()
//                .httpBasic().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .ignoringAntMatchers(
//                        "/eureka/**"
//                );
        // @formatter:on
    }
}
