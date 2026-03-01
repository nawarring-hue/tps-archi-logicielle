package com.ecommerce.monolith.order.mapper;

import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.dto.OrderItemDTO;
import com.ecommerce.monolith.order.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T22:27:07+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        Long customerId = null;
        LocalDateTime createdAt = null;
        BigDecimal total = null;
        List<OrderItemDTO> items = null;

        OrderDTO orderDTO = new OrderDTO( id, customerId, createdAt, total, items );

        return orderDTO;
    }
}
