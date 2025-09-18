package com.orion.mdd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.payload.request.PostCreateRequest;
import com.orion.mdd.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping()
    public ResponseEntity<List<PostDTO>> getPosts() {
        return new ResponseEntity<List<PostDTO>>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<PostDTO>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PostMapping()
    public String createPost(@RequestBody PostCreateRequest postCreateRequest) {

        return "Post Created";
    }

}
