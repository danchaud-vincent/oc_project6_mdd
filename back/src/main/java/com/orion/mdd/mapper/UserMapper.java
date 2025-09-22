package com.orion.mdd.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.RegisterRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topics", ignore = true)
    User toEntity(RegisterRequest registerRequest);

    UserDTO tDto(User user);

    List<UserDTO> toDto(List<User> userList);
}
