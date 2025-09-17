package com.orion.mdd.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.orion.mdd.model.Post;
import com.orion.mdd.model.Topic;
import com.orion.mdd.model.User;
import com.orion.mdd.model.Comment;
import com.orion.mdd.repository.CommentRepository;
import com.orion.mdd.repository.PostRepository;
import com.orion.mdd.repository.TopicRepository;
import com.orion.mdd.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

        private final UserRepository userRepo;
        private final TopicRepository topicRepo;
        private final PostRepository postRepo;
        private final CommentRepository commentRepo;

        public DataInitializer(UserRepository userRepo, TopicRepository topicRepo,
                        PostRepository postRepo, CommentRepository commentRepo) {
                this.userRepo = userRepo;
                this.topicRepo = topicRepo;
                this.postRepo = postRepo;
                this.commentRepo = commentRepo;
        }

        @Override
        public void run(String... args) throws Exception {
                // ADD USERS
                User user1 = User.builder()
                                .username("alice")
                                .email("alice@example.com")
                                .password("password1")
                                .build();

                User user2 = User.builder()
                                .username("bob")
                                .email("bob@example.com")
                                .password("password2")
                                .build();

                User user3 = User.builder()
                                .username("charlie")
                                .email("charlie@example.com")
                                .password("password3")
                                .build();

                userRepo.save(user1);
                userRepo.save(user2);
                userRepo.save(user3);

                // ADD TOPICS
                Topic topic1 = Topic.builder()
                                .title("Spring Boot")
                                .description("Discussion about Spring Boot projects")
                                .build();

                Topic topic2 = Topic.builder()
                                .title("Java")
                                .description("General Java programming topics")
                                .build();

                Topic topic3 = Topic.builder()
                                .title("Databases")
                                .description("All about SQL and NoSQL databases")
                                .build();

                topicRepo.save(topic1);
                topicRepo.save(topic2);
                topicRepo.save(topic3);

                // user subscriptions
                user1.setTopics(List.of(topic1, topic2));
                user2.setTopics(List.of(topic2, topic3));
                user3.setTopics(List.of(topic1, topic3));

                userRepo.save(user1);
                userRepo.save(user2);
                userRepo.save(user3);

                // ADD POSTS
                Post post1 = Post.builder()
                                .title("Getting started with Spring Boot")
                                .content("Spring Boot is amazing for REST APIs...")
                                .createdAt(LocalDateTime.now())
                                .author(user1)
                                .topic(topic1)
                                .build();

                Post post2 = Post.builder()
                                .title("Java Streams")
                                .content("Let’s talk about Java Streams API...")
                                .createdAt(LocalDateTime.now())
                                .author(user2)
                                .topic(topic2)
                                .build();

                Post post3 = Post.builder()
                                .title("Choosing the right database")
                                .content("QL vs NoSQL, which one to use?")
                                .createdAt(LocalDateTime.now())
                                .author(user3)
                                .topic(topic3)
                                .build();

                postRepo.save(post1);
                postRepo.save(post2);
                postRepo.save(post3);

                // ADD COMMENTS
                Comment comment1 = Comment.builder()
                                .content("Very informative post, thanks!")
                                .post(post1)
                                .createdBy(user1)
                                .build();

                Comment comment2 = Comment.builder()
                                .content("I prefer using functional programming with streams.")
                                .post(post2)
                                .createdBy(user2)
                                .build();

                Comment comment3 = Comment.builder()
                                .content("Great comparison between SQL and NoSQL!")
                                .post(post3)
                                .createdBy(user3)
                                .build();

                commentRepo.saveAll(List.of(comment1, comment2, comment3));
        }
}
