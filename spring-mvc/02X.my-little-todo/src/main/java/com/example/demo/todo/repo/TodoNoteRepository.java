package com.example.demo.todo.repo;

import com.example.demo.todo.entity.TodoNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoNoteRepository extends JpaRepository<TodoNote, Long> {
}
