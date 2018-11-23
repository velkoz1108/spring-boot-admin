package com.test.oauthserverdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author : wangtao
 * @date : 2018/11/22 19:41  星期四
 */

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("me")
    public Principal me(Principal principal) {
        return principal;
    }
}