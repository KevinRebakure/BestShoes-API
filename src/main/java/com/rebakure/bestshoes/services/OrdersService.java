package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.entities.Order;
import com.rebakure.bestshoes.entities.OrderItem;
import com.rebakure.bestshoes.exceptions.BadRequestException;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.OrderItemMapper;
import com.rebakure.bestshoes.mappers.OrderMapper;
import com.rebakure.bestshoes.repositories.*;
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
    private final VariantRepository variantRepository;

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
    @Transactional
    public OrderItemDto addItemToOrder(Long id, OrderItemRequest request) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found")
        );

        var product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new NotFoundException("Product not found")
        );

        OrderItem orderItem = new OrderItem();

        var existingItem = orderItemRepository.findOrderItemsByProductAndOrder(product, order);

        // TODO: add checks to prevent negative amounts

        if (existingItem.isPresent()) {
            var oldQuantity = existingItem.get().getQuantity();
            var newQuantity = oldQuantity + request.getQuantity();
            var basePrice = existingItem.get().getProduct().getBasePrice();

            var oldAmount = calculateTotalAmount(oldQuantity, basePrice);
            var newAmount = calculateTotalAmount(newQuantity, basePrice);


            existingItem.get().setQuantity(newQuantity);
            orderItemRepository.save(existingItem.get());

            order.setTotalAmount(
                    order.getTotalAmount()
                            .subtract(oldAmount)
                            .add(newAmount));
            orderRepository.save(order);
            return orderItemMapper.entityToDto(existingItem.get());

        } else {
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(request.getQuantity());
            orderItemRepository.save(orderItem);

            order.setTotalAmount(
                    order.getTotalAmount()
                            .add(calculateTotalAmount(request.getQuantity(), orderItem.getProduct().getBasePrice())));
            orderRepository.save(order);
            return orderItemMapper.entityToDto(orderItem);
        }

    }

    @Transactional
    public OrderItemDto updateOrderItem(
            Long id,
            Long itemId,
            UpdateOrderItemRequest request
    ) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order does not exist"));

        var item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item does not exist"));

        if (item.getQuantity() + request.getQuantity() < 1) {
            throw new BadRequestException("You must order at least 1 item");
        }

        var oldQuantity = item.getQuantity();
        var newQuantity = oldQuantity + request.getQuantity();
        var basePrice = item.getProduct().getBasePrice();

        var oldAmount = calculateTotalAmount(oldQuantity, basePrice);
        var newAmount = calculateTotalAmount(newQuantity, basePrice);

        item.setQuantity(newQuantity);
        orderItemRepository.save(item);

        order.setTotalAmount(
              order.getTotalAmount().subtract(oldAmount).add(newAmount)
        );

        orderRepository.save(order);
        return orderItemMapper.entityToDto(item);
    }


    public OrderItemDto findOrderItemById(Long id) {
        var item = orderItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item does not exist")
        );

        return orderItemMapper.entityToDto(item);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        var item = orderItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Item does not exist")
        );

        var amount = calculateTotalAmount(item.getQuantity(), item.getProduct().getBasePrice());

        var order = item.getOrder();
        order.setTotalAmount(order.getTotalAmount().subtract(amount));
        orderRepository.save(order);

        orderItemRepository.delete(item);
    }

    public List<OrderItemDto> findAllOrderItems(Long id) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order does not exist")
        );

        var items = orderItemRepository.findOrderItemsByOrder((order));
        return items.stream().map(orderItemMapper::entityToDto).toList();
    }


    private BigDecimal calculateTotalAmount(int numberOfItems, BigDecimal basePrice) {
        return basePrice.multiply(BigDecimal.valueOf(numberOfItems));
    }
}
