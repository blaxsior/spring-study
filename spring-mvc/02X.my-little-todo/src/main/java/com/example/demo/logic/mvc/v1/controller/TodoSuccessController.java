package com.example.demo.logic.mvc.v1.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.todo.entity.TodoNote;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;

public class TodoSuccessController implements ControllerV1 {
    private TodoNoteRepository todoRepository;
    public TodoSuccessController() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }
    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String content = req.getParameter("content");

        TodoNote todo = new TodoNote(content);
        todoRepository.save(todo);

        req.setAttribute("todo", todo);

        String path = "/WEB-INF/views/todo/create.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, res);
    }
}