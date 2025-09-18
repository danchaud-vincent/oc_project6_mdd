package com.orion.mdd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.payload.request.PostRequest;
import com.orion.mdd.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequest postCreateRequest) {

        PostDTO postDTO = postService.createPost(postCreateRequest);

        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Integer postId, @RequestBody String entity) {

        PostDTO postUpdatedDTO = postService.updatedPostById(postId);

        return new ResponseEntity<PostDTO>(postUpdatedDTO, HttpStatus.OK);
    }

}
