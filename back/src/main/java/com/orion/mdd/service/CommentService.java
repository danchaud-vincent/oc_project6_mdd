package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.mapper.CommentMapper;
import com.orion.mdd.model.Comment;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.CommentRequest;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;
import com.orion.mdd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPost(Integer postId) {
        // check if post exist
        postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        List<Comment> comments = commentRepository.findByPostId(postId);

        return commentMapper.toDto(comments);
    }

    public CommentDTO createComment(Integer postId, CommentRequest commentRequest) {
        // Get Post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not found with ID: " + postId));

        User user = userRepository.findById(commentRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + commentRequest.getAuthorId()));

        Comment comment = commentMapper.toEntity(commentRequest, user, post);

        Comment commentSaved = commentRepository.save(comment);

        return commentMapper.tDto(commentSaved);
    }

}
