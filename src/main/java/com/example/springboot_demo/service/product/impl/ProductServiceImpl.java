package com.example.springboot_demo.service.product.impl;

import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.repository.ProductRepository;
import com.example.springboot_demo.service.product.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(final Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "products", key = "#code")
    public Product getProductByCode(final String code) {
        return productRepository.getProductByCode(code);
    }

    @Override
    public List<Product> getProductsByName(final String name) {
        return productRepository.getProductsByName(name);
    }
}
