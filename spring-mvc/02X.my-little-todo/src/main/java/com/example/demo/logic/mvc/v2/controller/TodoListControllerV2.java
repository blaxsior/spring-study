package com.example.demo.logic.mvc.v2.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.MyView;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoListControllerV2 implements ControllerV2 {
    private TodoNoteRepository todoRepository;
    public TodoListControllerV2() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var todoList = this.todoRepository.findAll();
        req.setAttribute("todos", todoList);

        String path = "/WEB-INF/views/todo.jsp";
        return new MyView(path);
    }
}
