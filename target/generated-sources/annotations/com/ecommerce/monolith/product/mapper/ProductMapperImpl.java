package com.ecommerce.monolith.product.mapper;

import com.ecommerce.monolith.product.dto.CreateProductRequest;
import com.ecommerce.monolith.product.dto.ProductDTO;
import com.ecommerce.monolith.product.dto.UpdateProductRequest;
import com.ecommerce.monolith.product.model.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T22:27:07+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long categoryId = null;
        String categoryName = null;
        Long id = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        Integer stock = null;

        ProductDTO productDTO = new ProductDTO( id, name, description, price, stock, categoryId, categoryName );

        return productDTO;
    }

    @Override
    public Product fromCreate(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }

    @Override
    public void updateProductFromRequest(UpdateProductRequest request, Product product) {
        if ( request == null ) {
            return;
        }
    }
}
