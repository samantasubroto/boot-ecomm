package com.example.springboot_demo.service.user.impl;

import com.example.springboot_demo.config.SessionUser;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.repository.CustomerRepository;
import com.example.springboot_demo.repository.UserRepository;
import com.example.springboot_demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByEmail(final String customerId) {
        return this.customerRepository.getCustomerByEmail(customerId);
    }

    @Override
    public User getUserByEmail(final String userId) {
        return this.userRepository.getCustomerByEmail(userId);
    }

    @Override
    public void saveCustomer(final Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SessionUser sessionUser) {
            if (sessionUser.getEmail() != null) {
                return getCustomerByEmail(sessionUser.getEmail());
            }
        }
        return null;
    }
}
