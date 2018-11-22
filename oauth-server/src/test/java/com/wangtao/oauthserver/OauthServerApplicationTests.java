package com.wangtao.oauthserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthServerApplicationTests {

    @Test
    public void contextLoads() {
        String finalSecret = new BCryptPasswordEncoder().encode("123456"); // $2a$10$cOf2Mzy7Nr.kVsdP13HIyewvY7DviOorG/j73IKvkQ50Vo6aYptTy
        System.out.println("finalSecret = " + finalSecret);
    }

}
