package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.entities.Variant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantMapper {
   Variant requestToEntity(VariantRequest request);
   VariantDto  entityToDto(Variant variant);
}
