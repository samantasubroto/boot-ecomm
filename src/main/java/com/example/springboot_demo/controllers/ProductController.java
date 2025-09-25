package com.example.springboot_demo.controllers;

import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.service.product.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity(productService.getProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity getProductById(@PathVariable String code) {
        return new ResponseEntity(productService.getProductByCode(code), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getProductsByName(@RequestParam @NotNull String name) {
        return new ResponseEntity(productService.getProductsByName(name), HttpStatus.OK);
    }
}
