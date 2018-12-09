package com.wangtao.gatewayclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewayClient2Application {
    @RequestMapping("/hi")
    public String hi() {
        return "hi form client2";
    }


    public static void main(String[] args) {
        SpringApplication.run(GatewayClient2Application.class, args);
    }
}
