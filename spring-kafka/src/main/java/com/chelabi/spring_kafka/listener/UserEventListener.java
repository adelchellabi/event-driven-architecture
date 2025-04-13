package com.chelabi.spring_kafka.listener;

import com.chelabi.spring_kafka.constant.KafkaGroups;
import com.chelabi.spring_kafka.constant.UserTopics;
import com.chelabi.spring_kafka.dto.EventDTO;
import com.chelabi.spring_kafka.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {
    @KafkaListener(topics = UserTopics.USER_CREATED, groupId = KafkaGroups.EMAIL_SERVICE)
    public void handleUserCreated(EventDTO<UserCreatedEvent> event) {
        log.info("Send welcome email to: {}", event.payload());
    }

    @KafkaListener(topics = UserTopics.USER_REMOVED, groupId = KafkaGroups.EMAIL_SERVICE)
    public void handleUserRemoved(EventDTO<UserCreatedEvent> event) {
        log.info("Send goodbye email to: {}", event.payload());
    }
}
