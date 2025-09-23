package com.orion.mdd.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.exception.custom.ResourceNotFoundException;
import com.orion.mdd.mapper.CommentMapper;
import com.orion.mdd.mapper.PostMapper;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.PostRequest;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;
import com.orion.mdd.repository.TopicRepository;
import com.orion.mdd.repository.UserRepository;
import com.orion.mdd.security.model.CustomUserDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.findAll();

        return postMapper.toDto(posts);
    }

    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID %s not found ", postId)));

        return postMapper.toDto(post);
    }

    @Transactional
    public PostDTO createPost(PostRequest postCreateRequest, Authentication authentication) {
        // Get the user principal
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User userPrincipal = userDetails.getUser();

        // Find topic by id
        Topic topic = topicRepository.findById(postCreateRequest.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Topic with ID %s not found ", postCreateRequest.getTopicId())));

        // save the post
        Post newPost = postMapper.toEntity(postCreateRequest, userPrincipal, topic);
        Post newPostSaved = postRepository.save(newPost);

        return postMapper.toDto(newPostSaved);
    }

    @Transactional
    public PostDTO updatePostById(Integer postId, PostRequest postRequest) {
        // Get post by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID %s not found ", postId)));

        // Get topic by id
        Topic topic = topicRepository.findById(postRequest.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Topic with ID %s not found ", postRequest.getTopicId())));

        // update existing post
        postMapper.updateFromRequest(postRequest, post);
        post.setTopic(topic); // set the new topic if changed

        Post postSaved = postRepository.save(post);

        return postMapper.toDto(postSaved);
    }

    @Transactional
    public void deletePostById(Integer postId) {
        // Get the post
        Post postToDelete = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID %s not found ", postId)));

        postRepository.delete(postToDelete);
    }

}
