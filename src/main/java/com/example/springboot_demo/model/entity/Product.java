package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import com.example.springboot_demo.model.Price;
import com.example.springboot_demo.model.Stock;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product extends ItemType {

    @Size(min=7, max = 10, message = "Code must be between 7 and 10 character long")
    @Pattern(regexp = "\\d+", message = "Code must only contain numbers")
    private String code;

    private String name;

    private String description;

    private String comments;

    private boolean purchasable;

    @Embedded
    @Nullable
    private Price price;

    @Nullable
    private String image;

    @Embedded
    @Nullable
    private Stock stock;
}
