package com.example.springboot_demo.model;

import com.example.springboot_demo.model.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Price {

    private Currency currency;

    private double value;

    private boolean value_rounded;
}
