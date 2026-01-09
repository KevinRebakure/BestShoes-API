package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Order;
import com.rebakure.bestshoes.entities.OrderItem;
import com.rebakure.bestshoes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemsByOrder(Order order);

    Optional<OrderItem> findOrderItemsByProductAndOrder(Product product, Order order);
}
