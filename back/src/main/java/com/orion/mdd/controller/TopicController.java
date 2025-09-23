package com.orion.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.payload.request.TopicRequest;
import com.orion.mdd.service.TopicService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping()
    public ResponseEntity<List<TopicDTO>> getTopics() {

        Collection<TopicDTO> topicsDTO = topicService.getTopics();

        return new ResponseEntity<List<TopicDTO>>(new ArrayList<>(topicsDTO), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicRequest topicRequest) {

        TopicDTO topicDTO = topicService.createTopic(topicRequest);

        return new ResponseEntity<TopicDTO>(topicDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{topicId}/subscribe")
    public ResponseEntity<List<TopicDTO>> subscribe(@PathVariable Integer topicId, Authentication authentication) {

        Collection<TopicDTO> topicsDTO = topicService.subscribeToTopic(topicId, authentication);

        return new ResponseEntity<List<TopicDTO>>(new ArrayList<>(topicsDTO), HttpStatus.OK);
    }

}
