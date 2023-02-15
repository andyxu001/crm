package com.andy.api;

import com.andy.api.fallback.UserFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="crm-web",path = "/crm-web",fallback = UserFeignClientFallBack.class)
public interface UserFeignClient {

    @GetMapping("/getusers")
    String getUsers();
}
