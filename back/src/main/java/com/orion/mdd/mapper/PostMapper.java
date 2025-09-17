package com.orion.mdd.mapper;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.model.Post;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDTO, Post> {

}
