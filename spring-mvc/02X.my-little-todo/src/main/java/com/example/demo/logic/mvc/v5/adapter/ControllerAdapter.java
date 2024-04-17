package com.example.demo.logic.mvc.v5.adapter;

import com.example.demo.logic.mvc.MyModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerAdapter {
    boolean support(Object target);

    MyModelView handle(Object target, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
