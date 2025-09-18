package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.CommentDTO;
import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.mapper.CommentMapper;
import com.orion.mdd.mapper.PostMapper;
import com.orion.mdd.model.Comment;
import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.payload.request.PostRequest;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;
import com.orion.mdd.repository.TopicRepository;
import com.orion.mdd.repository.UserRepository;

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
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        return postMapper.toDto(post);
    }

    public PostDTO createPost(PostRequest postCreateRequest) {
        // Find user by id
        User user = userRepository.findById(postCreateRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " +
                        postCreateRequest.getAuthorId()));

        // Find topic by id
        Topic topic = topicRepository.findById(postCreateRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found by ID: " +
                        postCreateRequest.getTopicId()));

        // save the post
        Post newPost = postMapper.toEntity(postCreateRequest, user, topic);
        Post newPostSaved = postRepository.save(newPost);

        return postMapper.toDto(newPostSaved);
    }

    public PostDTO updatedPostById(Integer postId, PostRequest postRequest) {
        // Get post by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        // Get topic by id
        Topic topic = topicRepository.findById(postRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found with ID: " + postRequest.getTopicId()));

        // update existing post
        postMapper.updateFromRequest(postRequest, post);
        post.setTopic(topic); // set the new topic if changed

        Post postSaved = postRepository.save(post);

        return postMapper.toDto(postSaved);
    }

    public List<CommentDTO> getComments(Integer postId) {
        // check if post exist
        postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        List<Comment> comments = commentRepository.findByPostId(postId);

        return commentMapper.toDto(comments);
    }

}
