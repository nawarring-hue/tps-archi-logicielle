package com.ecommerce.monolith.order.service;

import com.ecommerce.monolith.customer.service.CustomerPublicService;
import com.ecommerce.monolith.order.dto.CreateOrderRequest;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.model.Order;
import com.ecommerce.monolith.order.model.OrderItem;
import com.ecommerce.monolith.order.repository.OrderRepository;
import com.ecommerce.monolith.product.service.ProductPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerPublicService customerPublicService;
    private final ProductPublicService productPublicService;

    private final com.ecommerce.monolith.order.mapper.OrderMapper mapper;

    public OrderDTO create(CreateOrderRequest req) {

        if (!customerPublicService.existsCustomer(req.customerId())) {
            throw new IllegalArgumentException("Customer not found: " + req.customerId());
        }

        Order order = new Order();
        order.setCustomerId(req.customerId());
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (var itemReq : req.items()) {

            if (!productPublicService.existsProduct(itemReq.productId())) {
                throw new IllegalArgumentException("Product not found: " + itemReq.productId());
            }

            BigDecimal price = productPublicService.getProductPrice(itemReq.productId());
            String name = productPublicService.getProductName(itemReq.productId());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemReq.productId());
            item.setQuantity(itemReq.quantity());
            item.setUnitPrice(price);
            item.setProductName(name);

            order.getItems().add(item);

            total = total.add(price.multiply(BigDecimal.valueOf(itemReq.quantity())));
        }

        order.setTotal(total);

        Order saved = orderRepository.save(order);
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> historyByCustomer(Long customerId) {
        return orderRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream().map(mapper::toDto).toList();
    }
}