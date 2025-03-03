package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends ItemType {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
//    private double price;
    private int quantity;
}
