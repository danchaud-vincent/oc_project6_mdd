package com.orion.mdd.mapper;

import org.mapstruct.Mapper;

import com.orion.mdd.dto.UserDTO;
import com.orion.mdd.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
