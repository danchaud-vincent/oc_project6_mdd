package com.orion.mdd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.mapper.PostMapper;
import com.orion.mdd.model.Post;
import com.orion.mdd.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getPosts() {

        List<Post> posts = postService.getPosts();

        return new ResponseEntity<List<PostDTO>>(postMapper.toDto(posts), HttpStatus.OK);
    }

}
