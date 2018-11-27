package com.wangtao.appmanager.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author : wangtao
 * @date : 2018/11/27 11:04  星期二
 */

@RestController
public class AdminController {


    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET})
    public String index() {
        return "Hello";
    }

    @RequestMapping(value = {"/user", "/me"}, method = {RequestMethod.GET})
    public String user(Principal principal) {
        return principal.getName();
    }
}
