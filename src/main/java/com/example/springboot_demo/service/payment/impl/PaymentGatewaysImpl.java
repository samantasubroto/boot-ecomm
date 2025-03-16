package com.example.springboot_demo.service.payment.impl;

import com.example.springboot_demo.model.entity.Order;
import com.example.springboot_demo.model.enums.OrderStatus;
import com.example.springboot_demo.service.payment.PaymentGateways;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewaysImpl implements PaymentGateways {

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    private RazorpayClient client;

    @Override
    @Transactional
    public Order createPayment(Order order) {
        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", order.getTotalPrice() * 100);
        orderReq.put("currency", "INR");
        orderReq.put("receipt", order.getCustomer().getEmail());
        try {
            this.client = new RazorpayClient(razorPayKey, razorPaySecret);
            com.razorpay.Order razorpayOrder = client.orders.create(orderReq);
            order.setRazorpayOrderId(razorpayOrder.get("id"));
            String statusString = razorpayOrder.get("status");
            OrderStatus status = OrderStatus.valueOf(statusString.toUpperCase()); // Convert to uppercase to match enum
            order.setStatus(status);
        } catch (RazorpayException r) {
            System.out.println("Exception Occured during checkout : " + r);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status received from Razorpay: " + e);
        }
        return order;
    }

    @Override
    @Transactional
    public String createPaymentLink(Order order) {
        JSONObject paymentLinkReq = new JSONObject();
        paymentLinkReq.put("amount", order.getTotalPrice() * 100);
        paymentLinkReq.put("currency", "INR");
        paymentLinkReq.put("description", "Payment for Order #" + order.getOrderNumber());

        JSONObject customerDetails = new JSONObject();
        customerDetails.put("name", order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
        customerDetails.put("email", order.getCustomer().getEmail());
        customerDetails.put("contact", "8801070401");

        paymentLinkReq.put("customer", customerDetails);
        paymentLinkReq.put("callback_url", "https://yourwebsite.com/payment-callback");
        paymentLinkReq.put("callback_method", "get");

        try {
            RazorpayClient client = new RazorpayClient(razorPayKey, razorPaySecret);
            com.razorpay.PaymentLink paymentLink = client.paymentLink.create(paymentLinkReq);
            return paymentLink.get("short_url");
        } catch (RazorpayException e) {
            throw new RuntimeException("Error generating payment link: " + e.getMessage());
        }
    }

}
