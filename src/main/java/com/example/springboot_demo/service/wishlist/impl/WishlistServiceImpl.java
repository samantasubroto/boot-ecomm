package com.example.springboot_demo.service.wishlist.impl;

import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.model.entity.WishList;
import com.example.springboot_demo.repository.WishlistRepository;
import com.example.springboot_demo.service.product.ProductService;
import com.example.springboot_demo.service.user.UserService;
import com.example.springboot_demo.service.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public WishList addProductToList(final String productId) {
        User user = userService.getCurrentUser();
        Product product = this.productService.getProductByCode(productId);
        if (user != null && product != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(user.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), user));
            if (!wishList.getProducts().contains(product)) {
                wishList.getProducts().add(product);
            }
            return wishlistRepository.save(wishList);
        }
        return null;
    }

    @Override
    public WishList removeProductFromList(final String productId) {
        User user = userService.getCurrentUser();
        Product product = this.productService.getProductByCode(productId);
        if (user != null && product != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(user.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), user));
            wishList.getProducts().remove(product);
            return wishlistRepository.save(wishList);
        }
        return null;
    }

    @Override
    public WishList getCustomersWishList() {
        User user = userService.getCurrentUser();
        if (user != null) {
            return wishlistRepository.findWishlistByCustomer(user.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), user));
        }
        return null;
    }

    @Override
    public void resetWishlist() {
        User user = userService.getCurrentUser();
        if (user != null) {
            WishList wishList = wishlistRepository.findWishlistByCustomer(user.getId())
                    .orElseGet(() -> new WishList(new ArrayList<>(), user));
            wishList.setProducts(null);
            wishlistRepository.save(wishList);
        }
    }
}
