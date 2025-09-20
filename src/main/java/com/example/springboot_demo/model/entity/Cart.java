package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends ItemType {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems = new ArrayList<>();

    private double cartTotal;

    @OneToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shipping_address")
    private Address shippingAddress;
}
