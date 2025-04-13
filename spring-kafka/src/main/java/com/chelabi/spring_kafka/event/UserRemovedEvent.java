package com.chelabi.spring_kafka.event;

import lombok.Builder;

@Builder
public record UserRemovedEvent(
        String id,
        String name,
        String email
) implements DomainEvent {
}
