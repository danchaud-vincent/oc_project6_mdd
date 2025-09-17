package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.dto.PostDTO;
import com.orion.mdd.mapper.PostMapper;
import com.orion.mdd.model.Post;
import com.orion.mdd.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.findAll();

        return postMapper.toDto(posts);
    }

    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        return postMapper.toDto(post);
    }

}
