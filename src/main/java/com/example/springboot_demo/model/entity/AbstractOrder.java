package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import com.example.springboot_demo.model.enums.OrderStatus;
import com.example.springboot_demo.model.enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractOrder extends ItemType {

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(
            name = "status",
            columnDefinition = "Describes about the order Status"
    )
    private OrderStatus status;

    @Column(
            name = "payment_method",
            columnDefinition = "Mode of payment"
    )
    private PaymentMethod paymentMethod;

    @Column(
            nullable = false,
            name = "total_price",
            columnDefinition = "The total price of the Order"
    )
    private double totalPrice;

    @Column(
            name = "total_discount",
            columnDefinition = "The total discount applied to the Order"
    )
    private double totalDiscount;

    @Column(
            name = "total_tax",
            columnDefinition = "The total tax applied to the Order"
    )
    private double totalTax;

    @Column(
            name = "expiration_time",
            columnDefinition = "Time of expiration"
    )
    private Date expirationTime;
}
