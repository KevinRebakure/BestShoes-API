package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.CollectionItemDto;
import com.rebakure.bestshoes.entities.CollectionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollectionItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "collection.id", target = "collectionId")
    CollectionItemDto entityToDto(CollectionItem item);
}
