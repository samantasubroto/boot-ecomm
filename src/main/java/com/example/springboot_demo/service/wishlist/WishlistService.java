package com.example.springboot_demo.service.wishlist;

import com.example.springboot_demo.model.entity.WishList;

public interface WishlistService {

    WishList addProductToList(final String productId, final String customerId);

    WishList removeProductFromList(final String productId, final String CustomerId);

    WishList getCustomersWishList(final String customerId);

    void resetWishlist(final String customerId);
}
