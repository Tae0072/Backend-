package com.example.guestbook.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "guestbook")
@Getter
@NoArgsConstructor
public class Guestbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @Builder
    public Guestbook(String author, String content) {
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
