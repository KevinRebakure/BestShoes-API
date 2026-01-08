package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Order;
import com.rebakure.bestshoes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
