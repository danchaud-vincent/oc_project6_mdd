package com.orion.mdd.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.model.Topic;
import com.orion.mdd.payload.request.TopicRequest;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "id", ignore = true)
    Topic toEntity(TopicRequest topicRequest);

    TopicDTO toDto(Topic topic);

    List<TopicDTO> toDto(List<Topic> topicList);

    List<Topic> toEntity(List<TopicDTO> topicDtoList);
}
