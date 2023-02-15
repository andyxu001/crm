package com.andy.api.fallback;

import com.andy.api.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements UserFeignClient {
    @Override
    public String getUsers() {
        return "由于网络拥堵，请稍后再试!";
    }
}
