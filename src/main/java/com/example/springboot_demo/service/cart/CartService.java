package com.example.springboot_demo.service.cart;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.User;

public interface CartService {
    Cart getUserCart(final String userId);

    Cart addProductToCart(final String productId, final String userId);
}
