package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.entities.Variant;
import com.rebakure.bestshoes.exceptions.ConflictException;
import com.rebakure.bestshoes.mappers.VariantMapper;
import com.rebakure.bestshoes.repositories.VariantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VariantsServiceTest {
    @Mock
    private VariantRepository variantRepository;

    @Mock
    private VariantMapper variantMapper;

    @InjectMocks
    private VariantsService variantsService;

    @Test
    void duplicateSku_rollsBackEntireSave() {
        VariantRequest  request = new VariantRequest();
        request.setBrand("Adidas");
        request.setColor("White");
        request.setMaterial("Mesh");
        request.setSize("41");
        request.setPrice(BigDecimal.valueOf(100));
        request.setQuantity(5);

        when(variantRepository.findUnique(
                "Adidas", "Mesh", "White", "41"
        )).thenReturn(Optional.of(new Variant()));

        assertThrows(ConflictException.class, () ->
                variantsService.addVariant(request)
        );

        verify(variantRepository, never()).save(any());
        verify(variantMapper, never()).requestToEntity(any());
    }


}