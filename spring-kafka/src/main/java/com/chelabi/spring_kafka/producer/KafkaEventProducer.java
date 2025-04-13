package com.chelabi.spring_kafka.producer;

import com.chelabi.spring_kafka.dto.EventDTO;
import com.chelabi.spring_kafka.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor

public class KafkaEventProducer {
    private final KafkaTemplate<String, EventDTO<?>> kafkaTemplate;

    public <T extends DomainEvent> void sendEvent(String topic, T payload) {
        EventDTO<T> event = EventDTO.<T>builder()
                .type(payload.getEventType())
                .payload(payload)
                .createdAt(Instant.now())
                .build();

        log.info("Sending event {} to topic : {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
}
