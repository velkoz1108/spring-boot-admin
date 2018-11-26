package com.wangtao.oauthserver.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangtao
 * @date : 2018/11/26 17:29  星期一
 */

public class JwtTokenEnhancer implements TokenEnhancer {
    /*jwt*/
    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String s = authentication.getName() + RandomStringUtils.randomAlphabetic(4);
        additionalInfo.put("organization", s);
        System.out.println("organization = " + s);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}