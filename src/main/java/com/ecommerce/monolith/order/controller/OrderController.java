package com.ecommerce.monolith.order.controller;

import com.ecommerce.monolith.order.dto.CreateOrderRequest;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/api/orders")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody CreateOrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping("/api/customers/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> history(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.historyByCustomer(customerId));
    }
}