package com.wangtao.appmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author WIN7
 */
@ServletComponentScan
@SpringBootApplication
public class AppManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppManagerApplication.class, args);
    }
}
