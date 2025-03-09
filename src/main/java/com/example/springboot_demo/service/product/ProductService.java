package com.example.springboot_demo.service.product;

import com.example.springboot_demo.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getProducts(final Pageable pageable);

    List<Product> getProductsByName(final String name);

    Product getProductByCode(final String productId);
}
