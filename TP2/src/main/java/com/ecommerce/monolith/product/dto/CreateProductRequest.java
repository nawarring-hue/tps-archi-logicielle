package com.ecommerce.monolith.product.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank String name,
        String description,
        @NotNull @Positive BigDecimal price,
        @NotNull @PositiveOrZero Integer stock,
        Long categoryId
) {}
