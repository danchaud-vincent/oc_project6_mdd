package com.orion.mdd.mapper;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.PostCreateRequest;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.username", target = "authorName")
    @Mapping(source = "topic.name", target = "topicName")
    PostDTO toDto(Post post);

    List<PostDTO> toDto(List<Post> posts);

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post toEntity(PostCreateRequest postRequest, User author, Topic topic);

}
