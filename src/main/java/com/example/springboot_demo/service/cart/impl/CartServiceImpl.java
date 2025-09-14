package com.example.springboot_demo.service.cart.impl;

import com.example.springboot_demo.model.entity.*;
import com.example.springboot_demo.model.enums.StockStatus;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.service.cart.CartService;
import com.example.springboot_demo.service.product.ProductService;
import com.example.springboot_demo.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
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
        return getOrCreateUserCart(customer);
    }

    @Override
    @Transactional
    public Cart addProductToCart(@NotNull final String productId, @NotNull final String userId) {
        Product product = productService.getProductByCode(productId);
        Customer customer = userService.getCustomerByEmail(userId);

        if (product == null || customer == null) {
            throw new RuntimeException("Invalid product or customer");
        }
        if (product.getStock().stockStatus.equals(StockStatus.OUTOFSTOCK)) {
            throw new RuntimeException("Product is out of stock!!!");
        }
        Cart cart = getOrCreateUserCart(customer);
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.stream().filter(
                        item -> item
                                .getProduct()
                                .getCode()
                                .equals(product.getCode()))
                .findFirst()
                .ifPresentOrElse((cartItem) -> {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                }, () -> {
                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(1);
                    cartItem.setCart(cart);
                    cart.getCartItems().add(cartItem);
                });

        setCartEntryNumberStartagy(cartItems);
        cart.setCartItems(cartItems);
        cart.setTotalPrice(Math.round(calculateCartTotal(cart)));
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
        cart.setTotalPrice(Math.round(calculateCartTotal(cart)));
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
        cart.setTotalPrice(Math.round(calculateCartTotal(cart)));
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
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    protected Cart getOrCreateUserCart(@NotNull final Customer customer) {
        Cart cart = cartRepository.getUsersCart(customer);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }
        return cart;
    }

    protected void setCartEntryNumberStartagy(List<CartItem> cartItems) {
        var itemNumber = 0;
        for (CartItem cartItem: cartItems) {
            cartItem.setEntryNumber(itemNumber++);
        }
    }

    public void saveCart(final Cart cart) {
        this.cartRepository.save(cart);
    }

    private double calculateCartTotal(Cart cart) {
        return cart.getCartItems().stream().mapToDouble(item -> item.getProduct().getPrice().getValue() * item.getQuantity()).sum();
    }
}
