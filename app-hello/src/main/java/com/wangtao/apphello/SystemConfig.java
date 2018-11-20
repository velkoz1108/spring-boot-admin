package com.wangtao.apphello;

import com.wangtao.apphello.entity.SystemInfo;
import com.wangtao.apphello.entity.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author : wangtao
 * @date : 2018/11/20 9:36  星期二
 */

@Component
@ConfigurationProperties(prefix = "system.info", ignoreInvalidFields = false, ignoreUnknownFields = false)
public class SystemConfig {
    private String author;
    private String email;
    private String blogAddress;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    @Bean
    public SystemInfo setInfo() {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setAuthor(author);
        systemInfo.setEmail(email);
        systemInfo.setBlogAddress(blogAddress);
        return systemInfo;
    }
}
