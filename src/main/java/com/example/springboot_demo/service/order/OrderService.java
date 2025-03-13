package com.example.springboot_demo.service.order;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Order;

public interface OrderService {

    public Order placeOrder(final String customerId);
}
