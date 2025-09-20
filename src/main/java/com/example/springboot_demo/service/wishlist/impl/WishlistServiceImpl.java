package com.example.springboot_demo.service.wishlist.impl;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.model.entity.WishList;
import com.example.springboot_demo.repository.WishlistRepository;
import com.example.springboot_demo.service.product.ProductService;
import com.example.springboot_demo.service.user.UserService;
import com.example.springboot_demo.service.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public WishList addProductToList(final String productId, final String customerId) {
        Customer customer = this.userService.getCustomerByEmail(customerId);
        Product product = this.productService.getProductByCode(productId);
        if (customer != null && product != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(customer.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), customer));
            if (!wishList.getProducts().contains(product)) {
                wishList.getProducts().add(product);
            }
            return wishlistRepository.save(wishList);
        }
        return null;
    }

    @Override
    public WishList removeProductFromList(final String productId, final String customerId) {
        Customer customer = this.userService.getCustomerByEmail(customerId);
        Product product = this.productService.getProductByCode(productId);
        if (customer != null && product != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(customer.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), customer));
            wishList.getProducts().remove(product);
            return wishlistRepository.save(wishList);
        }
        return null;
    }

    @Override
    public WishList getCustomersWishList(final String customerId) {
        Customer customer = this.userService.getCustomerByEmail(customerId);
        if (customer != null) {
            return wishlistRepository.findWishlistByCustomer(customer.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), customer));
        }
        return null;
    }

    @Override
    public void resetWishlist(final String customerId) {
        Customer customer = this.userService.getCustomerByEmail(customerId);
        if (customer != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(customer.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), customer));
            wishList.setProducts(null);
            wishlistRepository.save(wishList);
        }
    }
}
