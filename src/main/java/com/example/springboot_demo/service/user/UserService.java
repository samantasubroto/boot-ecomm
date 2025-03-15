package com.example.springboot_demo.service.user;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.User;

public interface UserService {

    Customer getCustomerByEmail(final String customerId);

    User getUserByEmail(final String userId);

    void saveCustomer(final Customer customer);
}
