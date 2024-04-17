package com.example.demo.logic.mvc.v1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoNewFormController implements ControllerV1 {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = "/WEB-INF/views/todo/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, res);
    }
}