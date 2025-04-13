package com.chelabi.spring_kafka.exception;

public class ResourceAlreadyExist extends RuntimeException {

    public ResourceAlreadyExist(String message) {
        super(message);
    }
}
