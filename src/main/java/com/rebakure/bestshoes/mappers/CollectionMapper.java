package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.CollectionDto;
import com.rebakure.bestshoes.entities.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper  {
    CollectionDto entityToDto(Collection collection);
}
