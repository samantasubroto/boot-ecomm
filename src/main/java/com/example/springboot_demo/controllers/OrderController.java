package com.example.springboot_demo.controllers;

import com.example.springboot_demo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder() {
        return new ResponseEntity(this.orderService.placeOrder(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        return new ResponseEntity(this.orderService.getOrders(), HttpStatus.FOUND);
    }
}
