package com.example.springboot_demo.service.authentication.impl;

import com.example.springboot_demo.exceptions.UserAlreadyExistsException;
import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.repository.CustomerRepository;
import com.example.springboot_demo.service.authentication.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    public Customer register(final Customer customer) {
        if (customerRepository.getCustomerByEmail(customer.getEmail()) == null) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
            this.cartRepository.save(cart);
            return this.customerRepository.save(customer);
        }
        throw new UserAlreadyExistsException("User Already Exists with same Email");
    }

    public String login(final String email, final String password) {
        Customer customer = this.customerRepository.getCustomerByEmail(email);
        if (customer != null) {
            if(password.equals(customer.getPassword())) {
                customer.setLastLogin(LocalDateTime.now());
                customerRepository.save(customer);
                return "success";
            } else {
                return "Bad Credentials";
            }
        } else {
            return "User not found";
        }
    }
}
