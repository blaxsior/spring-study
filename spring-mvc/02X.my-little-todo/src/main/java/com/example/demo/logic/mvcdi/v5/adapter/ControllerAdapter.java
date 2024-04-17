package com.example.demo.logic.mvcdi.v5.adapter;

import com.example.demo.logic.MyModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerAdapter {
    boolean support(Object target);

    MyModelView handle(Object target, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
