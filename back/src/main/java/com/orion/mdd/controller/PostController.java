package com.orion.mdd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.payload.request.CommentRequest;
import com.orion.mdd.payload.request.PostRequest;
import com.orion.mdd.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping()
    public ResponseEntity<List<PostDTO>> getPosts() {

        List<PostDTO> postsDTO = postService.getPosts();

        return new ResponseEntity<List<PostDTO>>(postsDTO, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {

        PostDTO postDTO = postService.getPostById(postId);

        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequest postRequest) {

        PostDTO postDTO = postService.createPost(postRequest);

        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Integer postId, @RequestBody PostRequest postRequest) {

        PostDTO postUpdatedDTO = postService.updatePostById(postId, postRequest);

        return new ResponseEntity<PostDTO>(postUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer postId) {

        postService.deletePostById(postId);

        return new ResponseEntity<String>(String.format("Post with ID %s is deleted!", postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Integer postId) {

        List<CommentDTO> commentsDTO = commentService.getCommentsByPost(postId);

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> addCommentToPost(@PathVariable Integer postId,
            @RequestBody CommentRequest commentRequest) {

        CommentDTO commentDTO = commentService.addCommentToPost(postId, commentRequest);

        return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public String deleteCommentById(@PathVariable Integer postId, @PathVariable Integer commentId) {

        return "deleted";
    }

}
