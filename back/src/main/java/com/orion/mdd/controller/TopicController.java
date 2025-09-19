package com.orion.mdd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.TopicDTO;
import com.orion.mdd.service.TopicService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping()
    public ResponseEntity<List<TopicDTO>> getTopics() {

        List<TopicDTO> topicsDTO = topicService.getTopics();

        return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
    }

}
