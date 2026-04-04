package com.ecommerce.monolith.product.mapper;

import com.ecommerce.monolith.product.dto.CreateProductRequest;
import com.ecommerce.monolith.product.dto.ProductDTO;
import com.ecommerce.monolith.product.dto.UpdateProductRequest;
import com.ecommerce.monolith.product.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {


    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    ProductDTO toDto(Product product);


    Product fromCreate(CreateProductRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(UpdateProductRequest request, @MappingTarget Product product);
}