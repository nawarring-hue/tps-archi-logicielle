package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.dto.ProductDTO;
import com.ecommerce.order_service.exception.OrderException;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Long productId, Integer quantity) {
        ProductDTO product;

        // Gérer le cas où le service Product est indisponible
        try {
            product = productClient.getProductById(productId);
        } catch (Exception e) {
            throw new OrderException("Service Product indisponible. Veuillez réessayer plus tard.");
        }

        // Gérer le cas où le produit n'existe pas
        if (product == null) {
            throw new OrderException("Produit non trouvé avec l'id : " + productId);
        }

        // Gérer le stock insuffisant
        if (product.getStock() < quantity) {
            throw new OrderException("Stock insuffisant ! Stock disponible : " + product.getStock());
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        Order savedOrder = orderRepository.save(order);

        // Mettre à jour le stock
        try {
            productClient.updateStock(productId, quantity);
        } catch (Exception e) {
            throw new OrderException("Erreur lors de la mise à jour du stock.");
        }

        return savedOrder;
    }
}