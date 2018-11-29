package com.wangtao.resourceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ResourceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceOrderApplication.class, args);
    }
}
