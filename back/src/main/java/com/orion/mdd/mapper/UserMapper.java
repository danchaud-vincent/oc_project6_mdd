package com.orion.mdd.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.RegisterRequest;
import com.orion.mdd.payload.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User toEntity(RegisterRequest registerRequest);

    UserDTO tDto(User user);

    List<UserDTO> toDto(List<User> userList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateFromRequest(UserRequest userRequest, @MappingTarget User existingUser);
}
