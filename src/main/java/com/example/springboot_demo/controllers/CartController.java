package com.example.springboot_demo.controllers;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public ResponseEntity getUserCart(@RequestParam(required = true) String userId) {
        return new ResponseEntity(cartService.getUserCart(userId), HttpStatus.OK);
    }

    @PostMapping("/addtocart")
    public ResponseEntity addProductToCart(@RequestParam(required = true) String productId, @RequestParam(required = true) String userId) {
        return new ResponseEntity(cartService.addProductToCart(productId, userId), HttpStatus.OK);
    }

    @PutMapping("/removefromcart")
    public ResponseEntity removeProductFromCart(@RequestParam(required = true) String productId, @RequestParam(required = true) String userId) {
        return new ResponseEntity(cartService.removeProductFromCart(productId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/deletefromcart")
    public ResponseEntity deleteProductFromCart(@RequestParam(required = true) String productId, @RequestParam(required = true) String userId) {
        return new ResponseEntity(cartService.deleteProductFromCart(productId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCart(@RequestParam(required = true) String userId) {
        return new ResponseEntity(cartService.deleteCart(userId), HttpStatus.OK);
    }
}
