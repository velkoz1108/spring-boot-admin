package com.wangtao.oauthclient.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : wangtao
 * @date : 2018/11/21 15:26  星期三
 * <p>
 * 获取用户信息的多种方法
 */

@RestController
public class UserController {
//    @RequestMapping("/user")
//    public Principal user(Principal principal) {
//        return principal;
//    }

    @RequestMapping({"/user", "/me"})
    public Map<String, String> user(Principal principal) {
        System.out.println("principal:" + principal.toString());
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @RequestMapping("/me/authentication")
    public Authentication authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @RequestMapping("/me/authentication2")
    public Authentication authentication2(Authentication authentication) {
        return authentication;
    }

    /**
     * 返回null了？
     * @param userDetails
     * @return
     */
    @RequestMapping("/me/userDetails")
    public UserDetails getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}


