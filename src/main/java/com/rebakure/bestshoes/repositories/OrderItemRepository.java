package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Order;
import com.rebakure.bestshoes.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemsByOrder(Order order);
}
