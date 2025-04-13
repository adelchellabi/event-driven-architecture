package com.chelabi.spring_kafka.exception.handler;

import com.chelabi.spring_kafka.dto.ExceptionResponseDTO;
import com.chelabi.spring_kafka.exception.BadRequestException;
import com.chelabi.spring_kafka.exception.ResourceAlreadyExist;
import com.chelabi.spring_kafka.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseDTO> handleBadRequest(BadRequestException ex) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceAlreadyExist(ResourceAlreadyExist ex) {
        return buildResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<Map<String, String>> errors = bindingResult.hasErrors()
                ? bindingResult.getAllErrors().stream().map(this::mapError).collect(Collectors.toList())
                : Collections.emptyList();

        ExceptionResponseDTO response = ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGenericException(Exception ex) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponseDTO> buildResponse(Exception ex, HttpStatus status) {
        com.chelabi.spring_kafka.dto.ExceptionResponseDTO response = ExceptionResponseDTO.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(response);
    }

    private Map<String, String> mapError(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return Map.of(
                    "field",
                    fieldError.getField(),
                    "message",
                    fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Validation error");
        }
        return Map.of(
                "message", error.getDefaultMessage() != null ? error.getDefaultMessage() : "Unknown validation error");
    }
}
