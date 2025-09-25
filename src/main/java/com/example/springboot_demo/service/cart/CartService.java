package com.example.springboot_demo.service.cart;

import com.example.springboot_demo.model.entity.Cart;

public interface CartService {
    Cart getUserCart();

    Cart addProductToCart(final String productId);

    Cart removeProductFromCart(final String productId);

    Cart deleteProductFromCart(final String productId);

    Cart deleteCart();

    Cart saveCart(final Cart cart);
}
