package com.orion.mdd.mapper;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.PostRequest;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.username", target = "authorName")
    @Mapping(source = "topic.name", target = "topicName")
    PostDTO toDto(Post post);

    List<PostDTO> toDto(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post toEntity(PostRequest postRequest, User author, Topic topic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "topic", ignore = true)
    void updateFromRequest(PostRequest postRequest, @MappingTarget Post existingPost);

}
