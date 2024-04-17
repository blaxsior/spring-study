package com.example.demo.logic.mvc.v2.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.MyView;
import com.example.demo.todo.entity.TodoNote;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoSuccessControllerV2 implements ControllerV2 {
    private TodoNoteRepository todoRepository;
    public TodoSuccessControllerV2() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String content = req.getParameter("content");

        TodoNote todo = new TodoNote(content);
        todoRepository.save(todo);

        req.setAttribute("todo", todo);

        String path = "/WEB-INF/views/todo/create.jsp";
        return new MyView(path);
    }
}