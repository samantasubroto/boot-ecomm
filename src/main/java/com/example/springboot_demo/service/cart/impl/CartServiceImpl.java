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
    public Cart getUserCart(String userId) {
        Customer customer = userService.getCustomerByEmail(userId);
        Cart userCart = cartRepository.getUsersCart(customer);
        return userCart;
    }

    @Override
    @Transactional
    public Cart addProductToCart(final String productId, final String userId) {
        Product product = productService.getProductByCode(productId);
        Customer customer = userService.getCustomerByEmail(userId);

        if (product == null || customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        if (product.getStock().stockStatus.equals(StockStatus.OUTOFSTOCK)) {
            throw new RuntimeException("Product is out of stock!!!");
        }
        Cart cart = getUserCart(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingItem = cartItems.stream().filter(item -> item.getProduct().getCode().equals(product.getCode())).findFirst();
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItems.add(cartItem);
        }

        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart removeProductFromCart(final String productId, final String userId) {
        Product product = productService.getProductByCode(productId);
        Customer customer = userService.getCustomerByEmail(userId);
        if (product == null || customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        Cart cart = getUserCart(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found!!!");
        }
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingItem = cartItems.stream().filter(item -> item.getProduct().getCode().equals(product.getCode())).findFirst();
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                cartItems.remove(cartItem);
            }
        } else {
            return cart;
        }
        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart deleteProductFromCart(final String productId, final String userId) {
        Product product = productService.getProductByCode(productId);
        Customer customer = userService.getCustomerByEmail(userId);
        if (product == null || customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        Cart cart = getUserCart(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found!!!");
        }
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingItem = cartItems.stream().filter(item -> item.getProduct().getCode().equals(product.getCode())).findFirst();
        existingItem.ifPresent(cartItems::remove);

        cart.setCartItems(cartItems);
        cart.setCartTotal(Math.round(calculateCartTotal(cart)));
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart deleteCart(final String userId) {
        Customer customer = userService.getCustomerByEmail(userId);
        if (customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        Cart cart = getUserCart(userId);
        if (cart == null) {
            return null;
        }
        cart.getCartItems().clear();
        cart.setCartTotal(0.0);
        return cartRepository.save(cart);
    }

    public void saveCart(final Cart cart) {
        this.cartRepository.save(cart);
    }

    private double calculateCartTotal(Cart cart) {
        return cart.getCartItems().stream().mapToDouble(item -> item.getProduct().getPrice().getValue() * item.getQuantity()).sum();
    }
}
