package com.orion.mdd.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.model.Comment;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.CommentRequest;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.username", target = "authorName")
    CommentDTO tDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "post", target = "post")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "commentRequest.content", target = "content")
    Comment toEntity(CommentRequest commentRequest, User author, Post post);

    List<CommentDTO> toDto(List<Comment> commentList);
}
