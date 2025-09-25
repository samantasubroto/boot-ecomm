package com.example.springboot_demo.service.payment;

import com.example.springboot_demo.model.entity.Order;

public interface PaymentGateways {

    Order createPayment(final Order order);

    String createPaymentLink(final Order order);
}
