package com.ecommerce.monolith.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerPublicServiceImpl implements CustomerPublicService {

    private final CustomerService customerService;

    @Override
    public boolean existsCustomer(Long customerId) {
        return customerService.existsCustomer(customerId);
    }
}