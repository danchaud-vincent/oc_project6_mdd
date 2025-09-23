package com.orion.mdd.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.TopicMapper;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.TopicRequest;
import com.orion.mdd.repository.TopicRepository;
import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
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

    public List<TopicDTO> subscribeToTopic(Integer topicId, Authentication authentication) {
        // Get user principal
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailPrincipal = jwt.getClaim("sub");

        User userPrincipal = userRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with email %s Not Found", emailPrincipal)));

        // Find topic
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Topic with ID %s Not Found", topicId)));

        // add topic to user
        userPrincipal.getTopics().add(topic);
        User userSaved = userRepository.save(userPrincipal);

        return topicMapper.toDto(userSaved.getTopics());
    }

}
