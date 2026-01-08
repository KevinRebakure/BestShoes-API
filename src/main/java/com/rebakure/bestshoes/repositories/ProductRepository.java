package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);

    List<Product> findProductsByNameLikeIgnoreCase(String name);
}
