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
                                .password("$2a$12$7ldozOyiHXESXFW3AVtAcumGhV2BJRTfFrbTuwa3Zsz.wJgNvlu3e") // password1
                                .build();

                User user2 = User.builder()
                                .username("bob")
                                .email("bob@example.com")
                                .password("$2a$12$ogSxnHhZ8THBw98UeAZJJOxcc1UsmqaNwA7LycHyRxsaWFn5GHTIe") // password2
                                .build();

                User user3 = User.builder()
                                .username("charlie")
                                .email("charlie@example.com")
                                .password("$2a$12$vSCsEuwOE1lr7488PkFXIORw4iBvfpNcDL07.oA8pMY7mB0FHJaKi") // password3
                                .build();

                userRepo.save(user1);
                userRepo.save(user2);
                userRepo.save(user3);

                // ADD TOPICS
                Topic topic1 = Topic.builder()
                                .name("Spring Boot")
                                .description("Discussion about Spring Boot projects")
                                .build();

                Topic topic2 = Topic.builder()
                                .name("Java")
                                .description("General Java programming topics")
                                .build();

                Topic topic3 = Topic.builder()
                                .name("Databases")
                                .description("All about SQL and NoSQL databases")
                                .build();

                Topic topic4 = Topic.builder()
                                .name("Python")
                                .description("Python, programming languages, OOP, etc.")
                                .build();

                Topic topic5 = Topic.builder()
                                .name("HTML/CSS")
                                .description("All about HTML and CSS")
                                .build();

                Topic topic6 = Topic.builder()
                                .name("Javascript")
                                .description("Learn to master JS with a bootcamp")
                                .build();

                topicRepo.save(topic1);
                topicRepo.save(topic2);
                topicRepo.save(topic3);
                topicRepo.save(topic4);
                topicRepo.save(topic5);
                topicRepo.save(topic6);

                // user subscriptions
                user1.getTopics().addAll(List.of(topic1, topic2));
                user2.getTopics().addAll(List.of(topic2, topic3));
                user3.getTopics().addAll(List.of(topic1, topic3));

                userRepo.save(user1);
                userRepo.save(user2);
                userRepo.save(user3);

                // ADD POSTS
                Post post1 = Post.builder()
                                .title("Getting started with Spring Boot")
                                .content("lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...")
                                .createdAt(LocalDateTime.of(2023, 5, 12, 10, 30, 0))
                                .author(user1)
                                .topic(topic1)
                                .build();

                Post post2 = Post.builder()
                                .title("Java Streams")
                                .content("lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...")
                                .createdAt(LocalDateTime.of(2025, 4, 26, 10, 11, 0))
                                .author(user2)
                                .topic(topic2)
                                .build();

                Post post3 = Post.builder()
                                .title("Choosing the right database")
                                .content("lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...")
                                .createdAt(LocalDateTime.of(2025, 1, 05, 15, 30, 0))
                                .author(user3)
                                .topic(topic3)
                                .build();

                Post post4 = Post.builder()
                                .title("HTML/CSS Best bootcamps")
                                .content("lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled...")
                                .createdAt(LocalDateTime.of(2024, 12, 5, 20, 30, 0))
                                .author(user3)
                                .topic(topic5)
                                .build();

                postRepo.save(post1);
                postRepo.save(post2);
                postRepo.save(post3);
                postRepo.save(post4);

                // ADD COMMENTS
                Comment comment1 = Comment.builder()
                                .content("Very informative post, thanks!")
                                .post(post1)
                                .author(user1)
                                .build();

                Comment comment2 = Comment.builder()
                                .content("I prefer using functional programming with streams.")
                                .post(post2)
                                .author(user2)
                                .build();

                Comment comment3 = Comment.builder()
                                .content("Great comparison between SQL and NoSQL!")
                                .post(post3)
                                .author(user3)
                                .build();

                commentRepo.saveAll(List.of(comment1, comment2, comment3));
        }
}
