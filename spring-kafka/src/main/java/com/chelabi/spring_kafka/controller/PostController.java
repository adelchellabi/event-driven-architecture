package com.chelabi.spring_kafka.controller;

import com.chelabi.spring_kafka.dto.PostCreateDTO;
import com.chelabi.spring_kafka.entity.Post;
import com.chelabi.spring_kafka.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "Post API")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@Valid @RequestBody PostCreateDTO postCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(postCreateDTO));

    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
