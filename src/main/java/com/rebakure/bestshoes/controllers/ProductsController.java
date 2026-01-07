package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.dtos.UpdateProductRequest;
import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.repositories.ProductRepository;
import com.rebakure.bestshoes.services.ProductsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Validated
public class ProductsController {
    private final ProductsService productsService;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductRequest request) {
        ProductDto dto = productsService.addProduct(request);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok().body(productsService.updateProduct(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(productsService.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productsService.findAllProducts());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        productsService.deleteProduct(id);
    }
}
