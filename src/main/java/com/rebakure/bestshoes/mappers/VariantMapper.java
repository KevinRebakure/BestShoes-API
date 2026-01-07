package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.UpdateVariantRequest;
import com.rebakure.bestshoes.dtos.VariantDto;
import com.rebakure.bestshoes.dtos.VariantRequest;
import com.rebakure.bestshoes.entities.Variant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VariantMapper {
   Variant requestToEntity(VariantRequest request);
   VariantDto entityToDto(Variant variant);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
   void update(UpdateVariantRequest request, @MappingTarget Variant variant);
}
