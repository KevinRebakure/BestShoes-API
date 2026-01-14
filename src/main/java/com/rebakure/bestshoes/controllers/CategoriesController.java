package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.UpdateCategoryRequest;
import com.rebakure.bestshoes.services.CategoriesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Validated
public class CategoriesController {
    private final CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @Valid  @RequestBody CategoryRequest request,
            UriComponentsBuilder uriBuilder) {
        var dto = categoriesService.addCategory(request);

        var uri = uriBuilder.path("/categories/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Integer id,
            @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok().body(categoriesService.updateCategory(id, request));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok().body(categoriesService.findAllCategories());
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDto>> getCategoryProducts(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok().body(categoriesService.findAllCategoryProducts(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Integer id
    ) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
