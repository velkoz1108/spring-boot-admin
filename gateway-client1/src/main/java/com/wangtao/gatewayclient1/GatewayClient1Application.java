package com.wangtao.gatewayclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewayClient1Application {

    @RequestMapping("/hi")
    public String hi() {
        return "hi form client1";
    }


    public static void main(String[] args) {
        SpringApplication.run(GatewayClient1Application.class, args);
    }
}
