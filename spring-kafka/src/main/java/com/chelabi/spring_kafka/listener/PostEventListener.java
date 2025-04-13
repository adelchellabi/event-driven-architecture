package com.chelabi.spring_kafka.listener;

import com.chelabi.spring_kafka.constant.KafkaGroups;
import com.chelabi.spring_kafka.constant.PostTopics;
import com.chelabi.spring_kafka.dto.EventDTO;
import com.chelabi.spring_kafka.event.PostCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostEventListener {
    @KafkaListener(topics = PostTopics.POST_CREATE, groupId = KafkaGroups.NOTIFICATION_SERVICE)
    public void handlePostCreated(EventDTO<PostCreatedEvent> event) {
        log.info("Notify users of new post: {}", event.payload());
    }
}
