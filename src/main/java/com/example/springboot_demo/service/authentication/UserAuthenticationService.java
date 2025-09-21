package com.example.springboot_demo.service.authentication;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.User;

import java.util.Map;

public interface UserAuthenticationService {

    User register(final User user);

    Map<String, Object> login(final String email, final String password);

    void logout(String uid);
}
