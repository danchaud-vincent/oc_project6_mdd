package com.orion.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "author.username", target = "authorName")
    CommentDTO tDto(Comment comment);
}
