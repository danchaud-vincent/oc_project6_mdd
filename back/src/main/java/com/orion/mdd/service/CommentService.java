package com.orion.mdd.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.CommentMapper;
import com.orion.mdd.model.Comment;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.CommentRequest;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;
import com.orion.mdd.repository.UserRepository;

import jakarta.transaction.Transactional;
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

    @Transactional
    public CommentDTO addCommentToPost(Integer postId, CommentRequest commentRequest, Authentication authentication) {
        // Get Post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not found with ID: " + postId));

        // Get User Principal
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailPrincipal = jwt.getClaim("sub");
        System.out.println("EMAIL SUB: " + emailPrincipal);

        User user = userRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with email %s Not Found", emailPrincipal)));

        Comment comment = commentMapper.toEntity(commentRequest, user, post);

        // save comment in the post
        Comment commentSaved = commentRepository.save(comment);

        return commentMapper.tDto(commentSaved);
    }

    @Transactional
    public void deletePostComment(Integer postId, Integer commentId) {
        // Get Post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not found with ID: " + postId));

        // Get Comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment Not found with ID: " + commentId));

        post.removeComment(comment);
    }

}
