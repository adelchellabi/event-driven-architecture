package com.chelabi.spring_kafka.mapper;


import com.chelabi.spring_kafka.dto.PostCreateDTO;
import com.chelabi.spring_kafka.entity.Post;
import com.chelabi.spring_kafka.event.PostCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toPost(PostCreateDTO dto) {
        return Post.builder()
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public PostCreatedEvent toPostCreatedEvent(Post post) {
        return PostCreatedEvent.builder()
                .id(post.getId().toString())
                .title(post.getTitle())
                .build();
    }
}
