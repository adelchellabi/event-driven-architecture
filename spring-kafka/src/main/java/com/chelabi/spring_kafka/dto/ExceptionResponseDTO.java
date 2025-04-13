package com.chelabi.spring_kafka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponseDTO {
    private int status;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private List<Map<String, String>> errors;
}
