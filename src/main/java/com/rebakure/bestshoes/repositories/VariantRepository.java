package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    Variant findVariantById(Long id);
}
