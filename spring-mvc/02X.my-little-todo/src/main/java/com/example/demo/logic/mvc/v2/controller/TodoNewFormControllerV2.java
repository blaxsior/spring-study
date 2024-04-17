package com.example.demo.logic.mvc.v2.controller;

import com.example.demo.logic.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoNewFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = "/WEB-INF/views/todo/new-form.jsp";
        return new MyView(path);
    }
}