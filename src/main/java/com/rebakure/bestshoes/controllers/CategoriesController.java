package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.CategoryRequest;
import com.rebakure.bestshoes.services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
