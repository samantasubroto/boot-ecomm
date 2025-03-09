package com.example.springboot_demo.model;

import com.example.springboot_demo.model.enums.StockStatus;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Stock implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    public StockStatus stockStatus;
    public int stockLevel;
}