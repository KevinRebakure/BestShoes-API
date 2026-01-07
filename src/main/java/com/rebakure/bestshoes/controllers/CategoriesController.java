package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.dtos.UpdateCategoryRequest;
import com.rebakure.bestshoes.services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryRequest request) {
        var dto = categoriesService.addCategory(request);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok().body(categoriesService.updateCategory(id, request));
    }
}
