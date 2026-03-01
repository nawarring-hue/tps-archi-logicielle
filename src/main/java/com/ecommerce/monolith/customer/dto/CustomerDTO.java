package com.ecommerce.monolith.customer.dto;

public record CustomerDTO(
        Long id,
        String fullName,
        String email
) {}
