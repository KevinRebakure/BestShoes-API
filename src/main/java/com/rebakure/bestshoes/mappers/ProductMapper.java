package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.UpdateProductRequest;
import com.rebakure.bestshoes.entities.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);
}