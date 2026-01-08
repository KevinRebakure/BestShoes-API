package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.OrderDto;
import com.rebakure.bestshoes.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto entityToDto(Order order);
}
