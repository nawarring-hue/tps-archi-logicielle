package com.ecommerce.monolith.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long customerId,
        LocalDateTime createdAt,
        BigDecimal total,
        List<OrderItemDTO> items
) {}