package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.dtos.UpdateCategoryRequest;
import com.rebakure.bestshoes.services.CategoriesService;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Validated
public class CategoriesController {
    private final CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryRequest request) {
        var dto = categoriesService.addCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

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
