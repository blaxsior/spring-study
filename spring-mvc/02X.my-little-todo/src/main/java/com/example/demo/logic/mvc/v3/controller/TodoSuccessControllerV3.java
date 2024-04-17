package com.example.demo.logic.mvc.v3.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.logic.mvc.MyView;
import com.example.demo.todo.entity.TodoNote;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class TodoSuccessControllerV3 implements ControllerV3 {
    private TodoNoteRepository todoRepository;
    public TodoSuccessControllerV3() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public MyModelView process(Map<String, String> params) throws ServletException, IOException {
        String content = params.get("content");

        TodoNote todo = new TodoNote(content);
        todoRepository.save(todo);

        MyModelView modelView = new MyModelView("/todo/create");
        modelView.addAttribute("todo", todo);
        return modelView;
    }
}