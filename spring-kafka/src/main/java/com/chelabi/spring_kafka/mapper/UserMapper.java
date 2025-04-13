package com.chelabi.spring_kafka.mapper;

import com.chelabi.spring_kafka.dto.UserCreateDTO;
import com.chelabi.spring_kafka.entity.User;
import com.chelabi.spring_kafka.event.UserCreatedEvent;
import com.chelabi.spring_kafka.event.UserRemovedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreateDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .build();
    }

    public UserCreatedEvent toUserCreatedEvent(User user) {
        return UserCreatedEvent.builder()
                .id(String.valueOf(user.getId()))
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserRemovedEvent toUserRemovedEvent(User user) {
        return UserRemovedEvent.builder()
                .id(String.valueOf(user.getId()))
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
