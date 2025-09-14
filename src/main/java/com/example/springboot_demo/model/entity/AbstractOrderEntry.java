package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractOrderEntry extends ItemType {

    @Column(
            name="entry_number"
    )
    private int entryNumber;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(
            name="quantity"
    )
    private int quantity;

    @Column(
            nullable = true,
            name="price_at_purchase"
    )
    private double priceAtPurchase;
}
