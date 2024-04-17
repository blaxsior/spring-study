package com.example.demo.logic.mvc.v4.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.todo.entity.TodoNote;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class TodoSuccessControllerV4 implements ControllerV4 {
    private TodoNoteRepository todoRepository;
    public TodoSuccessControllerV4() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public String process(Map<String, String> params, MyModel model) throws ServletException, IOException {
        String content = params.get("content");

        TodoNote todo = new TodoNote(content);
        todoRepository.save(todo);

        model.addAttribute("todo", todo);
        return "todo/create";
    }
}