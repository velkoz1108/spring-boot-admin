package com.wangtao.apphello.feign;

import com.wangtao.apphello.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : wangtao
 * @date : 2018/10/21 9:57  星期日
 */

@FeignClient(value = "auth-server", configuration = FeignConfig.class)
public interface UserService {
    @RequestMapping(value = "/api/user/validate", method = RequestMethod.GET)
    public String validate(@RequestParam("username") String username);

    @RequestMapping(value = "/api/user/save", method = RequestMethod.POST)
    public String save(@RequestBody UserInfo userInfo);
}
