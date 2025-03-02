package com.example.springboot_demo.model;

import com.example.springboot_demo.model.enums.StockStatus;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Stock {
    public StockStatus stockStatus;
    public int stockLevel;
}