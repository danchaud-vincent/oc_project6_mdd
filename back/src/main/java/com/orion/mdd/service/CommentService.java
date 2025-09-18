package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.mapper.CommentMapper;
import com.orion.mdd.model.Comment;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPost(Integer postId) {
        // check if post exist
        postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        List<Comment> comments = commentRepository.findByPostId(postId);

        return commentMapper.toDto(comments);
    }

}
