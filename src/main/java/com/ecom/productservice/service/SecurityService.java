package com.ecom.productservice.service;

import com.ecom.productservice.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {
    private final SecurityServiceClient securityServiceClient;

    public UserModel getCurrentUser(String token) {
        return this.securityServiceClient.getCurrentUser(token);
    }
}
