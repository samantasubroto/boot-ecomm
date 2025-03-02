package com.example.springboot_demo.service.product;

import com.example.springboot_demo.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> getProducts(Pageable pageable);

    Product getProductById(Long code);
}
