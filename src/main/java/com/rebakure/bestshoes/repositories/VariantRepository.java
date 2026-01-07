package com.rebakure.bestshoes.repositories;

import com.rebakure.bestshoes.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    Variant findVariantById(Long id);

    @Query("SELECT v FROM Variant v WHERE v.brand = :brand " +
            "AND v.material = :material " +
            "AND v.color = :color " +
            "AND v.size = :size")
    Optional<Variant> findUnique(
            @Param("brand") String brand,
            @Param("material") String material,
            @Param("color") String color,
            @Param("size") String size);
}
