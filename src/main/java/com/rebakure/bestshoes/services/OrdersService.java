package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.entities.Order;
import com.rebakure.bestshoes.entities.OrderItem;
import com.rebakure.bestshoes.exceptions.BadRequestException;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.OrderItemMapper;
import com.rebakure.bestshoes.mappers.OrderMapper;
import com.rebakure.bestshoes.repositories.OrderItemRepository;
import com.rebakure.bestshoes.repositories.OrderRepository;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;

    @Transactional
    public OrderDto addOrder(OrderRequest request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        Order order = new Order();
        order.setUser(user);
        order.setPlacedOn(LocalDate.now());
        order.setTotalAmount(BigDecimal.ZERO);

        orderRepository.save(order);
        return orderMapper.entityToDto(order);
    }

    public OrderDto findOrderById(Long id) {
        var order = orderRepository.findById((id)).orElseThrow(() -> new NotFoundException("Order not found"));

        return orderMapper.entityToDto(order);
    }

    public List<OrderDto> findAllOrders(Long userId) {
        if (userId == null) {
            return orderRepository.findAll().stream().map(orderMapper::entityToDto).toList();
        }

        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        return orderRepository.findAllByUser(user).stream().map(orderMapper::entityToDto).toList();
    }

    public void deleteOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found")
        );

        orderRepository.delete(order);
    }

    // Order items

    public OrderItemDto addItemToOrder(Long id, OrderItemRequest request) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found")
        );

        var product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new NotFoundException("Product not found")
        );

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        orderItemRepository.save(item);
        return orderItemMapper.entityToDto(item);
    }

    public OrderItemDto updateOrderItem(
            Long id,
            Long itemId,
            UpdateOrderItemRequest request
    ) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist"));

        var item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item does not exist"));

        if (request.getProductId() != null) {
            var product = productRepository.findProductById(request.getProductId());
            if (product == null) {
                throw new NotFoundException("Product does not exist");
            }
            item.setProduct(product);
        }

        if (request.getQuantity() != null) {
            var quantity = request.getQuantity();
            if (item.getQuantity() + quantity < 1) {
                throw new BadRequestException("You must order at least 1 item");
            }
            item.setQuantity(item.getQuantity() + quantity);
        }

        orderItemRepository.save(item);
        return orderItemMapper.entityToDto(item);
    }


    public OrderItemDto findOrderItemById(Long id) {
        var item = orderItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item does not exist")
        );

        return orderItemMapper.entityToDto(item);
    }

    public void deleteOrderItem(Long id) {
        var item = orderItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item does not exist")
        );

        orderItemRepository.delete(item);
    }

    public List<OrderItemDto> findAllOrderItems(Long id) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order does not exist")
        );

        var items = orderItemRepository.findOrderItemsByOrder((order));
        return items.stream().map(orderItemMapper::entityToDto).toList();
    }
}
