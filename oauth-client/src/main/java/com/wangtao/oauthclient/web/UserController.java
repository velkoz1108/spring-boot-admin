package com.wangtao.oauthclient.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : wangtao
 * @date : 2018/11/21 15:26  星期三
 */

@RestController
public class UserController {
//    @RequestMapping("/user")
//    public Principal user(Principal principal) {
//        return principal;
//    }

    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }
}


