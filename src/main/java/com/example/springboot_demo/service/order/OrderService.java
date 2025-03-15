package com.example.springboot_demo.service.order;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(final String customerId);

    List<Order> getOrders(final String customerId);
}
