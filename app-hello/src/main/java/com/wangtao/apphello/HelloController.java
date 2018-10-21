package com.wangtao.apphello;

import com.wangtao.apphello.entity.UserInfo;
import com.wangtao.apphello.feign.UserService;
import io.micrometer.core.instrument.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangtao
 * @date : 2018/10/15 16:25  星期一
 */

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        UserInfo info = new UserInfo();
        info.setUsername("test" + Math.random());
        info.setPassword("pwd" + Math.random());
        return userService.save(info);
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/validate")
    public String validate(@RequestParam("username") String username) {
        return userService.validate(username);
    }
}
