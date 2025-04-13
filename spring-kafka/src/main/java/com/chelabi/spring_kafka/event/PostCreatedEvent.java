package com.chelabi.spring_kafka.event;

import lombok.Builder;

@Builder
public record PostCreatedEvent(String id, String title) implements DomainEvent {
}
