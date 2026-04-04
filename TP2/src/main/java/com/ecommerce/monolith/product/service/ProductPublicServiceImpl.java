package com.ecommerce.monolith.product.service;

import com.ecommerce.monolith.product.model.Product;
import com.ecommerce.monolith.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductPublicServiceImpl implements ProductPublicService {

    private final ProductRepository repo;

    @Override
    public boolean existsProduct(Long productId) {
        return repo.existsById(productId);
    }

    @Override
    public BigDecimal getProductPrice(Long productId) {
        Product p = repo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        return p.getPrice();
    }

    @Override
    public String getProductName(Long productId) {
        Product p = repo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        return p.getName();
    }
}