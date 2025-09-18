package com.orion.mdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orion.mdd.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostId(Integer postId);

}
