package com.rebakure.bestshoes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "variants")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Size(max = 50)
    @NotNull
    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Size(max = 50)
    @NotNull
    @Column(name = "size", nullable = false, length = 50)
    private String size;

    @Size(max = 50)
    @NotNull
    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @NotNull
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Size(max = 255)
    @NotNull
    @Column(name = "stock_keeping_unit", nullable = false)
    private String stockKeepingUnit;

    @OneToMany(mappedBy = "variant")
    private Set<Product> products = new LinkedHashSet<>();

}