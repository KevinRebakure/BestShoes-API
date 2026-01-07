package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.services.VariantsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/variants")
@AllArgsConstructor
public class VariantsController {
    private final VariantsService variantsService;

    @PostMapping
    public ResponseEntity<VariantDto> addVariant(@Valid @RequestBody VariantRequest request) {
        var dto = variantsService.addVariant(request);
        return ResponseEntity.ok(dto);
    }
}
