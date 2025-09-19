package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.mapper.TopicMapper;
import com.orion.mdd.model.Topic;
import com.orion.mdd.payload.request.TopicRequest;
import com.orion.mdd.repository.TopicRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicDTO> getTopics() {

        List<Topic> topics = topicRepository.findAll();

        return topicMapper.toDto(topics);
    }

    public TopicDTO createTopic(TopicRequest topicRequest) {

        Topic newTopic = topicMapper.toEntity(topicRequest);
        Topic topicSaved = topicRepository.save(newTopic);

        return topicMapper.toDto(topicSaved);
    }

}
