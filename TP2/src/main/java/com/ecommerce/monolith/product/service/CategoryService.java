package com.ecommerce.monolith.product.service;

import com.ecommerce.monolith.product.model.Category;
import com.ecommerce.monolith.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }

    public Category create(Category category) {
        return repository.save(category);
    }
}