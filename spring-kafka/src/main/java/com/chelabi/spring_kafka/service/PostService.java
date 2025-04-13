package com.chelabi.spring_kafka.service;

import com.chelabi.spring_kafka.dto.PostCreateDTO;
import com.chelabi.spring_kafka.entity.Post;
import com.chelabi.spring_kafka.exception.ResourceAlreadyExist;
import com.chelabi.spring_kafka.mapper.PostMapper;
import com.chelabi.spring_kafka.producer.KafkaEventProducer;
import com.chelabi.spring_kafka.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.chelabi.spring_kafka.constant.PostTopics.POST_CREATE;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final KafkaEventProducer kafkaEventProducer;
    private final PostMapper postMapper;

    @Transactional
    public Post createPost(PostCreateDTO postCreateDTO) {
        checkIfPostExistsByTitle(postCreateDTO.title());

        Post post = postRepository.save(postMapper.toPost(postCreateDTO));
        kafkaEventProducer.sendEvent(POST_CREATE, postMapper.toPostCreatedEvent(post));

        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    private void checkIfPostExistsByTitle(String title) {
        postRepository.findByTitle(title)
                .ifPresent(post -> {
                    throw new ResourceAlreadyExist("Post with title '" + title + "' already exists");
                });
    }
}
