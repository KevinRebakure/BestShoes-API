package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.dtos.UpdateProductRequest;
import com.rebakure.bestshoes.services.ProductsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Validated
@Tag(name = "Products")
public class ProductsController {
    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(
            @Valid @RequestBody ProductRequest request,
            UriComponentsBuilder uriBuilder) {
        ProductDto dto = productsService.addProduct(request);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
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
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            @Min(value = 1, message = "Price has to be at least $1")
            BigDecimal maxPrice
    ) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(productsService.findProductByName(name));
        }

        if (maxPrice != null) {
            return ResponseEntity.ok(productsService.findProductByMaxPrice(maxPrice));
        }

        return ResponseEntity.ok().body(productsService.findAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
