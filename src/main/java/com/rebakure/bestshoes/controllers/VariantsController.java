package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.UpdateVariantRequest;
import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.services.VariantsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variants")
@AllArgsConstructor
@Validated
public class VariantsController {
    private final VariantsService variantsService;

    @PostMapping
    public ResponseEntity<VariantDto> addVariant(@Valid @RequestBody VariantRequest request) {
        var dto = variantsService.addVariant(request);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VariantDto> updateVariant(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody UpdateVariantRequest request) {
        return ResponseEntity.ok().body(variantsService.updateVariant(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantDto> getVariant(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(variantsService.findVariantById(id));
    }

    @GetMapping
    public ResponseEntity<List<VariantDto>> getVariant() {
        return ResponseEntity.ok().body(variantsService.findAllVariants());
    }
}
