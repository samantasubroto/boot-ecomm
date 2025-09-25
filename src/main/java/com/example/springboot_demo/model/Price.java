package com.example.springboot_demo.model;

import com.example.springboot_demo.model.enums.Currency;
import jakarta.persistence.*;
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
public class Price implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Currency currency;

    private double value;

    private boolean value_rounded;
}
