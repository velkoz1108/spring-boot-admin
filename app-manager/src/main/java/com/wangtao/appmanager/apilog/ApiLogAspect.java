//package com.wangtao.appmanager.apilog;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
///**
// * @author : wangtao
// * @date : 2018/11/23 9:42  星期五
// */
//@Slf4j
//@Aspect
//@Component
//public class ApiLogAspect {
//    @Autowired
//    private ApiLogService apiLogService;
//
//    @Pointcut("execution(* com.wangtao.appmanager.web.*.*(..))")
//    private void controllerAspect() {
//    }
//
//    /**
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("controllerAspect()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object object = pjp.proceed();
//        long usedTime = System.currentTimeMillis() - start;
//        //记录日志
//        saveApiLog(pjp, usedTime);
//        return object;
//    }
//
//    private void saveApiLog(ProceedingJoinPoint pjp, long usedTime) {
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            log.info("URI : " + JSON.toJSONString(request.getRequestURI()));
//
//            String remote = IpAddressUtil.getIP(request);
//            log.info("remote : " + remote);
//
//            String ip = IpAddressUtil.getIpAddress();
//            log.info("localip : " + ip);
//
//            Object target = pjp.getTarget();
//            log.info("target: " + target.toString());
//
//            String methodName = pjp.getSignature().getName();
//            log.info("method : " + methodName);
//
//            Object[] args = pjp.getArgs();
//            log.info("args : " + JSON.toJSONString(args));
//
//            Date date = new Date();
//            log.info("time : " + date.toString());
//
//            apiLogService.saveApiLog();
//        } catch (Exception e) {
//            log.info("SaveApiLog Exception", e);
//        }
//    }
//}
