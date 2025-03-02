package com.example.springboot_demo.service.product.impl;

import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.repository.ProductRepository;
import com.example.springboot_demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long code) {
       return productRepository.getProductById(code);
    }
}
