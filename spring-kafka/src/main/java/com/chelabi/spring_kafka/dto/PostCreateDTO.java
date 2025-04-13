package com.chelabi.spring_kafka.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PostCreateDTO(
        @NotEmpty
        @NotNull
        String title,
        @NotEmpty
        @NotNull
        String content
) {
}
