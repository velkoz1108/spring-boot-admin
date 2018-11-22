package com.wangtao.oauthservercode;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : wangtao
 * @date : 2018/11/22 15:24  星期四
 */

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/oauth/**", "/", "/login**", "/webjars/**").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
//        ;

        http
                .authorizeRequests().antMatchers("/oauth/**", "/login/**", "/logout", "/webjars/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
