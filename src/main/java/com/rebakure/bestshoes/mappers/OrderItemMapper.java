package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.OrderItemDto;
import com.rebakure.bestshoes.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    OrderItemDto entityToDto(OrderItem item);
}
