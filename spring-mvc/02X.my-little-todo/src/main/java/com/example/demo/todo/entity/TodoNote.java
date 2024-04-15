package com.example.demo.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "todo_note")
@EntityListeners(AuditingEntityListener.class)
public class TodoNote {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String content;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    public TodoNote(@NonNull String content) {
        this.content = content;
    }
}
