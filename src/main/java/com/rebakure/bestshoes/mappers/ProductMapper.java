package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto entityToDto(Product product);
}
