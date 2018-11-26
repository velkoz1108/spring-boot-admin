package com.wangtao.oauthserver.config;

import com.wangtao.oauthserver.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author : wangtao
 * @date : 2018/11/1 11:10  星期四
 */
//@Order(5)
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 在 security config 中配置了 super.authenticationManagerBean()
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * {bcrypt} 方案在验证密码时报错，不知道是不是版本的问题
         */
        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        /**
         * 配置基于内存的客户端
         *
         * client_id
         * secret
         * resourceIds
         * authorizedGrantTypes     该client允许的授权类型  client_credentials, refresh_token, authorization_code, password
         * scopes                   允许的授权范围
         */
        clients.inMemory()
                .withClient("client_1")
                .secret(finalSecret)
                .resourceIds(Constant.RESOURCE_ORDER, Constant.RESOURCE_CLIENT1)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .authorities("ROLE_CLIENT1")
                .scopes("read", "trust")
                .redirectUris("http://www.baidu.com")
                .and()

                .withClient("client_2")
                .resourceIds(Constant.RESOURCE_ORDER, Constant.RESOURCE_CLIENT2)
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ROLE_CLIENT2")
                .scopes("read", "trust")
                .secret(finalSecret)
                .and()

                .withClient("client_3")
                .secret(finalSecret)
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .resourceIds(Constant.RESOURCE_ORDER)
                .authorities("ROLE_USER")
                .scopes("read", "write", "trust")
                .redirectUris("http://localhost:8080/", "http://localhost:8080/login/admin")
                .and()

                .withClient("client_4")
                .secret(finalSecret)
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .resourceIds(Constant.RESOURCE_ORDER, Constant.RESOURCE_CLIENT2)
                .authorities("ROLE_CLIENT2")
                .scopes("read", "trust")
                .redirectUris("http://localhost:8080/user", "https://www.baidu.com", "https://www.qq.com")
                .autoApprove(true)
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                //token 存入 redis
//                .tokenStore(new MyRedisTokenStore(redisConnectionFactory))
                .tokenStore(new InMemoryTokenStore())
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .userDetailsService(userDetailsService)
        ;

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .realm(Constant.RESOURCE_CLIENT2)
                //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}
