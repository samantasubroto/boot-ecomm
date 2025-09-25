package com.example.springboot_demo.service.wishlist;

import com.example.springboot_demo.model.entity.WishList;

public interface WishlistService {

    WishList addProductToList(final String productId);

    WishList removeProductFromList(final String productId);

    WishList getCustomersWishList();

    void resetWishlist();
}
