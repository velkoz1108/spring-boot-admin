package com.wangtao.resourceorder.guest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangtao
 * @date : 2018/11/29 16:11  星期四
 */
@Slf4j
@RestController
public class GuestController {
    @GetMapping("/guest/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authentication:{}", authentication);
        return "guest id : " + id;
    }
}
