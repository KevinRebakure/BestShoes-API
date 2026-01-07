package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.CategoryDto;
import com.rebakure.bestshoes.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto entityToDto(Category category);
}
