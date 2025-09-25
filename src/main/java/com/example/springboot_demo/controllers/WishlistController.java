package com.example.springboot_demo.controllers;

import com.example.springboot_demo.model.entity.WishList;
import com.example.springboot_demo.service.wishlist.WishlistService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity addProductToList(@RequestParam @NotNull String productId) {
        return new ResponseEntity<>(wishlistService.addProductToList(productId), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity removeProductFromList(@RequestParam @NotNull String productId) {
        return new ResponseEntity<>(wishlistService.removeProductFromList(productId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getCustomersWishlist() {
        return new ResponseEntity<>(wishlistService.getCustomersWishList(), HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetWishlist() {
        wishlistService.resetWishlist();
        return ResponseEntity.ok().build();
    }
}
