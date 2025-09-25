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
    public ResponseEntity getUserCart() {
        return new ResponseEntity(cartService.getUserCart(), HttpStatus.OK);
    }

    @PostMapping("/addtocart")
    public ResponseEntity addProductToCart(@RequestParam(required = true) String productId) {
        return new ResponseEntity(cartService.addProductToCart(productId), HttpStatus.OK);
    }

    @PutMapping("/removefromcart")
    public ResponseEntity removeProductFromCart(@RequestParam(required = true) String productId) {
        return new ResponseEntity(cartService.removeProductFromCart(productId), HttpStatus.OK);
    }

    @DeleteMapping("/deletefromcart")
    public ResponseEntity deleteProductFromCart(@RequestParam(required = true) String productId) {
        return new ResponseEntity(cartService.deleteProductFromCart(productId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCart() {
        return new ResponseEntity(cartService.deleteCart(), HttpStatus.OK);
    }
}
