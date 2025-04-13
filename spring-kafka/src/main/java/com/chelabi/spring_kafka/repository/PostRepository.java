package com.chelabi.spring_kafka.repository;

import com.chelabi.spring_kafka.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findByTitle(String title);
}

