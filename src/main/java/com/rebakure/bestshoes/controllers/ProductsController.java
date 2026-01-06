package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.services.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductRequest request) {
        ProductDto dto = productsService.addProduct(request);
        return ResponseEntity.ok().body(dto);
    }
}
