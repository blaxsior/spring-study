package com.example.demo.logic.mvc.v2.controller;

import com.example.demo.ApplicationContextProvider;
import com.example.demo.logic.mvc.MyView;
import com.example.demo.todo.repo.TodoNoteRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoDeleteControllerV2 implements ControllerV2 {
    private TodoNoteRepository todoRepository;
    public TodoDeleteControllerV2() {
        this.todoRepository = ApplicationContextProvider.getBean(TodoNoteRepository.class);
    }

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var deleteId = Long.parseLong(req.getParameter("deleteId"));
        this.todoRepository.deleteById(deleteId);
        // 값 설정
        req.setAttribute("deleteId", deleteId);

        String path = "/WEB-INF/views/todo/delete.jsp";
        return new MyView(path);
    }
}