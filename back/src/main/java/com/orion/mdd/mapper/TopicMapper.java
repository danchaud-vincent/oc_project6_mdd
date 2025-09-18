package com.orion.mdd.mapper;

import org.mapstruct.Mapper;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.model.Topic;

@Mapper(componentModel = "spring")
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

}
