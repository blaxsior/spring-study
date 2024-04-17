package com.example.demo.logic.mvc.v1.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;

public class TodoDeleteController implements ControllerV1 {
    private TodoNoteRepository todoRepository;
    public TodoDeleteController() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var deleteId = Long.parseLong(req.getParameter("deleteId"));
        this.todoRepository.deleteById(deleteId);
        // 값 설정
        req.setAttribute("deleteId", deleteId);

        String path = "/WEB-INF/views/todo/delete.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, res);
    }
}