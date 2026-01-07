package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.dtos.UpdateCategoryRequest;
import com.rebakure.bestshoes.entities.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto entityToDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateCategoryRequest request, @MappingTarget Category category);
}
