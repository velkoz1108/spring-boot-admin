package com.wangtao.apphello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangtao
 * @date : 2018/10/15 16:25  星期一
 */

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Hello World From App-Hello";
    }
}
