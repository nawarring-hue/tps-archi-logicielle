package com.ecommerce.monolith.customer.service;

import com.ecommerce.monolith.customer.dto.CreateCustomerRequest;
import com.ecommerce.monolith.customer.dto.CustomerDTO;
import com.ecommerce.monolith.customer.mapper.CustomerMapper;
import com.ecommerce.monolith.customer.model.Customer;
import com.ecommerce.monolith.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public CustomerDTO getById(Long id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
        return mapper.toDto(c);
    }

    public CustomerDTO create(CreateCustomerRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already used: " + req.email());
        }
        Customer saved = repo.save(mapper.fromCreate(req));
        return mapper.toDto(saved);
    }

    // ✅ required: verify existence
    @Transactional(readOnly = true)
    public boolean existsCustomer(Long id) {
        return repo.existsById(id);
    }
}