package com.wangtao.oauthserver.config;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * @author : wangtao
 * @date : 2018/11/4 15:20  星期日
 *
 * jsr250Enabled    -->  RolesAllowed
 *
 * securedEnabled   -->  Secured
 *
 */

@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
