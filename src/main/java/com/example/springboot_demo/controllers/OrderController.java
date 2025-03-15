package com.example.springboot_demo.controllers;

import com.example.springboot_demo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestParam(required = true) String userId) {
        return new ResponseEntity(this.orderService.placeOrder(userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam(required = true) String userId) {
        return new ResponseEntity(this.orderService.getOrders(userId), HttpStatus.FOUND);
    }
}
