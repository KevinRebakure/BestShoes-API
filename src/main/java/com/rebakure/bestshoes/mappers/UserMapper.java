package com.rebakure.bestshoes.mappers;

import com.rebakure.bestshoes.dtos.UserDto;
import com.rebakure.bestshoes.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);
}
