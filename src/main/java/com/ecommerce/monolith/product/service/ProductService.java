package com.ecommerce.monolith.product.service;

import com.ecommerce.monolith.product.dto.CreateProductRequest;
import com.ecommerce.monolith.product.dto.ProductDTO;
import com.ecommerce.monolith.product.dto.UpdateProductRequest;
import com.ecommerce.monolith.product.mapper.ProductMapper;
import com.ecommerce.monolith.product.model.Category;
import com.ecommerce.monolith.product.model.Product;
import com.ecommerce.monolith.product.repository.CategoryRepository;
import com.ecommerce.monolith.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    // GET ALL
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDtoWithCategory)
                .toList();
    }

    // GET BY ID
    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        return toDtoWithCategory(product);
    }

    // CREATE
    public ProductDTO create(CreateProductRequest request) {

        Product product = mapper.fromCreate(request);

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.categoryId()));
            product.setCategory(category);
        }

        Product saved = repository.save(product);

        return toDtoWithCategory(saved);
    }

    // UPDATE
    public ProductDTO update(Long id, UpdateProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        mapper.updateProductFromRequest(request, product);

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found: " + request.categoryId()));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        Product saved = repository.save(product);

        return toDtoWithCategory(saved);
    }

    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // =============================
    // PRIVATE MAPPING HELPER
    // =============================
    private ProductDTO toDtoWithCategory(Product p) {

        Long categoryId = null;
        String categoryName = null;

        if (p.getCategory() != null) {
            categoryId = p.getCategory().getId();
            categoryName = p.getCategory().getName();
        }

        return new ProductDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                categoryId,
                categoryName
        );
    }
}
