package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.UpdateProductRequest;
import com.rebakure.bestshoes.entities.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "variant.color", target = "color")
    @Mapping(source = "variant.size", target = "size")
    @Mapping(source = "variant.material", target = "material")
    @Mapping(source = "variant.brand", target = "brand")
    @Mapping(source = "variant.stockKeepingUnit", target = "stockKeepingUnit")
    ProductDto entityToDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);
}
