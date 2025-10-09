package com.orion.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orion.mdd.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
