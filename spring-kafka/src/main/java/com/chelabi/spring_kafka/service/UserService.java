package com.chelabi.spring_kafka.service;

import com.chelabi.spring_kafka.constant.UserTopics;
import com.chelabi.spring_kafka.dto.UserCreateDTO;
import com.chelabi.spring_kafka.entity.User;
import com.chelabi.spring_kafka.exception.ResourceAlreadyExist;
import com.chelabi.spring_kafka.exception.ResourceNotFoundException;
import com.chelabi.spring_kafka.mapper.UserMapper;
import com.chelabi.spring_kafka.producer.KafkaEventProducer;
import com.chelabi.spring_kafka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaEventProducer kafkaEventProducer;
    private final UserMapper userMapper;

    public User createUser(UserCreateDTO userCreateDTO) {
        checkIfUserExistsByEmail(userCreateDTO.email());

        var user = userRepository.save(userMapper.toUser(userCreateDTO));
        kafkaEventProducer.sendEvent(UserTopics.USER_CREATED, userMapper.toUserCreatedEvent(user));

        return user;
    }

    public List<User> getAllPosts() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
        kafkaEventProducer.sendEvent(UserTopics.USER_REMOVED, userMapper.toUserRemovedEvent(user));
    }

    private void checkIfUserExistsByEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new ResourceAlreadyExist("User with email '" + email + "' already exists");
                });
    }
}
