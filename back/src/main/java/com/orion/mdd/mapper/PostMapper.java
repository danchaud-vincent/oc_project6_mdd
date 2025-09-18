package com.orion.mdd.mapper;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.PostCreateRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Override
    @Mapping(source = "author.username", target = "authorName")
    @Mapping(source = "topic.name", target = "topicName")
    PostDTO toDto(Post post);

    @Override
    @Mapping(source = "authorName", target = "author.username")
    @Mapping(source = "topicName", target = "topic.name")
    Post toEntity(PostDTO postDTO);

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post toEntity(PostCreateRequest postRequest, User author, Topic topic);

}
