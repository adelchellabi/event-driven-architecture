package com.chelabi.spring_kafka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserCreateDTO(
        @NotNull @NotEmpty String name, @NotNull @Email String email) {
}
