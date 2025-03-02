package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import jakarta.persistence.Entity;
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
    private String code;
    private String name;
    private double price;
    private int quantity;
}
