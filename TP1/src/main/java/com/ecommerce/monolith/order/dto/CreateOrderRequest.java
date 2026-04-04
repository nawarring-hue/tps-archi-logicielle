package com.ecommerce.monolith.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(
        @NotNull Long customerId,
        @Size(min = 1) List<OrderItemRequest> items
) {}