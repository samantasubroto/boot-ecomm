package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import com.example.springboot_demo.model.enums.OrderStatus;
import com.example.springboot_demo.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order extends ItemType{

    @Column(nullable = false, unique = true, length = 10)
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    private double totalPrice;
}
