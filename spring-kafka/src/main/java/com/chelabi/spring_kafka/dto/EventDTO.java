package com.chelabi.spring_kafka.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record EventDTO<T>(String type, T payload, Instant createdAt) {
}
