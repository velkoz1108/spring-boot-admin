package com.wangtao.oauthserver.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

/**
 * @author : wangtao
 * @date : 2018/10/31 11:23  星期三
 */
@RestController
public class HomeController {
    @Secured("ROLE_ADMIN")
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/user")
    public Principal user(Principal user, @RequestParam(name = "code", required = false) String code) {
        if (!StringUtils.isEmpty(code)) {
            System.out.println("oauth-authorize code is : " + code);
        }
        return user;
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @RolesAllowed("ROLE_ORDER")
    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @RolesAllowed("ROLE_CLIENT1")
    @GetMapping("/client1/product/{id}")
    public String getClientProduct1(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @RolesAllowed("ROLE_CLIENT1")
    @GetMapping("/client1/order/{id}")
    public String getclientOrder1(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @RolesAllowed("ROLE_CLIENT2")
    @GetMapping("/client2/product/{id}")
    public String getClientProduct2(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @RolesAllowed("ROLE_CLIENT2")
    @GetMapping("/client2/order/{id}")
    public String getclientOrder2(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
}
