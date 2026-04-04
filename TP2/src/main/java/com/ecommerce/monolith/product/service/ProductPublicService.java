package com.ecommerce.monolith.product.service;

import java.math.BigDecimal;

public interface ProductPublicService {
    boolean existsProduct(Long productId);
    BigDecimal getProductPrice(Long productId);
    String getProductName(Long productId);
}