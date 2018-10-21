package com.wangtao.authserver.api;

import com.wangtao.authserver.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author : wangtao
 * @date : 2018/10/21 9:49  星期日
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class AuthController {

    @RequestMapping(value = "/user/validate", method = RequestMethod.GET)
    @ResponseBody
    public String validateUser(@RequestParam("username") String username) {
        log.info("[/api/user/validate]username is {} .", username);
        return "validate success";
    }


    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody UserInfo userInfo) {
        log.info("[/api/user/save]userInfo is {} .", userInfo.toString());
        return "save success";
    }

}
