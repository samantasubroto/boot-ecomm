package com.example.springboot_demo.service.cart.impl;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.CartItem;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.repository.CustomerRepository;
import com.example.springboot_demo.repository.ProductRepository;
import com.example.springboot_demo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart getUserCart(String userId) {
        Customer customer = customerRepository.getCustomerByEmail(userId);
        Cart userCart = cartRepository.getUsersCart(customer);
        return userCart;
    }

    @Override
    public Cart addProductToCart(final String productId, final String userId) {
        Product product = productRepository.getProductByCode(productId);
        Customer customer = customerRepository.getCustomerByEmail(userId);

        if (product == null || customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        Cart cart = cartRepository.getUsersCart(customer);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
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
        cart.setCartTotal(calculateCartTotal(cart));
        return cartRepository.save(cart);
    }

    private double calculateCartTotal(Cart cart) {
        return cart.getCartItems().stream().mapToDouble(item -> item.getProduct().getPrice().getValue() * item.getQuantity()).sum();
    }
}
