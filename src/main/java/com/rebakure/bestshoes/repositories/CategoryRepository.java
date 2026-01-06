package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
