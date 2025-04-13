package com.chelabi.spring_kafka.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Lob
    private String content;
}
