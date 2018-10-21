package com.wangtao.authserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : wangtao
 * @date : 2018/10/21 12:02  星期日
 */

@Slf4j
public class FeignReuqestHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        log.info("[FeignReuqestHandler.preHandle]Authorization in header is {} .", authorization);
        return true;

    }
}
