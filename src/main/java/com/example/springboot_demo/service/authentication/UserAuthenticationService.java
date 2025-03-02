package com.example.springboot_demo.service.authentication;

import com.example.springboot_demo.model.entity.Customer;

public interface UserAuthenticationService {

    Customer register(final Customer customer);

    String login(final String email, final String password);
}
