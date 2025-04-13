package com.chelabi.spring_kafka.event;

public interface DomainEvent {
    default String getEventType() {
        return this.getClass().getSimpleName();
    }
}
