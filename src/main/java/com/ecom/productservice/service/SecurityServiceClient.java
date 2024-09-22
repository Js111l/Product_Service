package com.ecom.productservice.service;

import com.ecom.productservice.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SecurityService", url = "http://localhost:8080/auth")
public interface SecurityServiceClient {
    @GetMapping("/context/current-user")
    UserModel getCurrentUser(@RequestHeader("Authorization") String token);

}
