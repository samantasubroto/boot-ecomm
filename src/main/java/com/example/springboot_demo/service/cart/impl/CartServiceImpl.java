package com.example.springboot_demo.service.cart.impl;

import com.example.springboot_demo.model.entity.*;
import com.example.springboot_demo.model.enums.StockStatus;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.service.cart.CartService;
import com.example.springboot_demo.service.product.ProductService;
import com.example.springboot_demo.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public Cart getUserCart() {
        User user = userService.getCurrentUser();
        return getOrCreateUserCart(user);
    }

    @Override
    @Transactional
    public Cart addProductToCart(final String productId) {
        Product product = productService.getProductByCode(productId);
        User user = userService.getCurrentUser();

        if (product == null || user == null) {
            throw new RuntimeException("Invalid product or user");
        }
        if (product.getStock().stockStatus.equals(StockStatus.OUTOFSTOCK)) {
            throw new RuntimeException("Product is out of stock!!!");
        }
        Cart cart = getOrCreateUserCart(user);
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.stream()
                .filter(item -> item.getProduct().getCode().equals(product.getCode()))
                .findFirst()
                    .ifPresentOrElse((cartItem) -> {
                         cartItem.setQuantity(cartItem.getQuantity() + 1);
                    }, () -> {
                        CartItem cartItem = new CartItem();
                        cartItem.setProduct(product);
                        cartItem.setQuantity(1);
                        cartItems.add(cartItem);
                    });

        OrderEntryNumberStatergy(cart);
        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));

        return saveCart(cart);
    }


    @Override
    @Transactional
    public Cart removeProductFromCart(final String productId) {
        Product product = productService.getProductByCode(productId);
        User user = userService.getCurrentUser();
        if (product == null || user == null) {
            throw new RuntimeException("Invalid product or user");
        }
        Cart cart = getOrCreateUserCart(user);
        if (cart == null) {
            throw new RuntimeException("Cart not found!!!");
        }
        List<CartItem> cartItems = cart.getCartItems();

        cartItems.stream().filter(item -> item.getProduct().getCode().equals(product.getCode()))
                .findFirst()
                .ifPresentOrElse((cartItem) -> {
                    if (cartItem.getQuantity() > 1) {
                        cartItem.setQuantity(cartItem.getQuantity() - 1);
                    } else {
                        cartItems.remove(cartItem);
                    }
                }, () -> {});

        OrderEntryNumberStatergy(cart);
        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));
        return saveCart(cart);
    }

    @Override
    @Transactional
    public Cart deleteProductFromCart(final String productId) {
        Product product = productService.getProductByCode(productId);
        User user = userService.getCurrentUser();
        if (product == null || user == null) {
            throw new RuntimeException("Invalid product or user");
        }
        Cart cart = getOrCreateUserCart(user);
        if (cart == null) {
            throw new RuntimeException("Cart not found!!!");
        }
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingItem = cartItems.stream().filter(item -> item.getProduct().getCode().equals(product.getCode())).findFirst();
        existingItem.ifPresent(cartItems::remove);

        OrderEntryNumberStatergy(cart);
        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));
        return saveCart(cart);
    }

    @Override
    @Transactional
    public Cart deleteCart() {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new RuntimeException("Invalid product or user");
        }
        Cart cart = getOrCreateUserCart(user);
        if (cart == null) {
            return null;
        }
        cart.getCartItems().clear();
        cart.setCartTotal(0.0);
        return saveCart(cart);
    }

    public Cart saveCart(final Cart cart) {
        return this.cartRepository.save(cart);
    }

    private Cart getOrCreateUserCart(final User user) {
        Cart cart = cartRepository.getUsersCart(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        return cart;
    }

    private double calculateCartTotal(final Cart cart) {
        return cart.getCartItems().stream().mapToDouble(item -> item.getProduct().getPrice().getValue() * item.getQuantity()).sum();
    }

    private void OrderEntryNumberStatergy(final Cart cart) {
        int entryNumber = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setOrderEntryNumber(entryNumber++);
        }
    }
}
