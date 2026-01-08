package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.UpdateVariantRequest;
import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.exceptions.ConflictException;
import com.rebakure.bestshoes.exceptions.NotFoundException;
import com.rebakure.bestshoes.mappers.VariantMapper;
import com.rebakure.bestshoes.repositories.VariantRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VariantsService {
    private VariantRepository variantRepository;
    private VariantMapper variantMapper;

    public VariantDto addVariant(@Valid VariantRequest request) {
       var variant = variantRepository.findUnique(
               request.getBrand(),
               request.getMaterial(),
               request.getColor(),
               request.getSize()
       );

       if(variant.isPresent()){
           throw new ConflictException("Variant already exists");
       }

       // Handling other database errors like unique constraint on sku column
        try {
            var result = variantMapper.requestToEntity(request);
            variantRepository.save(result);

            return variantMapper.entityToDto(result);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Variant already exists or the store keeping unit is occupied");
        }
    }

    @Transactional
    public VariantDto updateVariant(Long id, UpdateVariantRequest request) {
        var variant = variantRepository.findVariantById(id);

        if (variant == null) {
            throw new NotFoundException("Variant not found");
        }

        variantMapper.update(request,variant);
        variantRepository.save(variant);
        return variantMapper.entityToDto(variant);
    }

    public VariantDto findVariantById(Long id) {
        var variant = variantRepository.findVariantById(id);

        if (variant == null) {
            throw new NotFoundException("Variant not found");
        }

        return variantMapper.entityToDto(variant);
    }

    public List<VariantDto> findAllVariants() {
        return variantRepository.findAll().stream().map(variantMapper::entityToDto).toList();
    }

    public void deleteVariant(Long id) {
        var variant = variantRepository.findVariantById(id);

        if (variant == null) {
            throw new NotFoundException("Variant not found");
        }

        variantRepository.delete(variant);
    }
}
