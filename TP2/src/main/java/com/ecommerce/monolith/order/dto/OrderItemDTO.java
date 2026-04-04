package com.ecommerce.monolith.order.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice
) {}