package com.wangtao.oauthclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 */
@EnableOAuth2Sso
@EnableAuthorizationServer
@SpringBootApplication
public class OauthClientApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(OauthClientApplication.class, args);
    }

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and().logout().logoutSuccessUrl("/").permitAll()

                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
        ;
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filters.add(ssoFilter(myself(), "/login/myself"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("ClientId : " + client.getClient().getClientId());
        System.out.println("AccessTokenUri : " + client.getClient().getAccessTokenUri());
        System.out.println("GrantType : " + client.getClient().getGrantType());
        System.out.println("TokenName : " + client.getClient().getTokenName());
        System.out.println("Scope : " + client.getClient().getScope());
        System.out.println("UserAuthorizationUri: " + client.getClient().getUserAuthorizationUri());
        System.out.println("UseCurrentUri : " + client.getClient().isUseCurrentUri());
        System.out.println("TokenType : " + client.getResource().getTokenType());
        System.out.println("UserInfoUri : " + client.getResource().getUserInfoUri());
        System.out.println("-----------------------------------------------");
        System.out.println();
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        ClientResources clientResources = new ClientResources();
        return clientResources;
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        ClientResources clientResources = new ClientResources();
        return clientResources;
    }

    @Bean
    @ConfigurationProperties("myself")
    public ClientResources myself() {
        ClientResources clientResources = new ClientResources();
        return clientResources;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
