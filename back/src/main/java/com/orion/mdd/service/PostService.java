package com.orion.mdd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orion.mdd.model.Post;
import com.orion.mdd.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();

        return posts;
    }

}
