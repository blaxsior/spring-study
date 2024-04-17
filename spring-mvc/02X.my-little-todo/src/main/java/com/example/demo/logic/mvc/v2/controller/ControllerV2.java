package com.example.demo.logic.mvc.v2.controller;

import com.example.demo.logic.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
