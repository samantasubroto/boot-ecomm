package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(
    name = "cart_id",
    uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "order_entry_number"})
)
public class CartItem extends ItemType {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    @Column(name = "order_entry_number", nullable = false)
    private int orderEntryNumber;
}
