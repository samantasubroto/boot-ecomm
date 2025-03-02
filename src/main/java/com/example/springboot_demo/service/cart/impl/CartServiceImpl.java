package com.example.springboot_demo.service.cart.impl;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.repository.CustomerRepository;
import com.example.springboot_demo.repository.ProductRepository;
import com.example.springboot_demo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        Cart userCart =  cartRepository.getUsersCart(customer);
        if (userCart != null) {
            return userCart;
        }
        return null;
    }

    @Override
    public Cart addProductToCart(final String productId, final String userId) {
        Product product = productRepository.getProductByCode(productId);
            Customer customer = customerRepository.getCustomerByEmail(userId);

        try {
            if(product != null && customer != null) {
                Cart cart = cartRepository.getUsersCart(customer);
                Set<Product> products = cart.getProducts();
                products.add(product);
                cart.setProducts(products);
                cartRepository.save(cart);
                return cart;
            }
        } catch (Exception e) {
            System.out.println("Error while adding product to cart "+e.getMessage().toString());
        }
        return null;
    }
}
