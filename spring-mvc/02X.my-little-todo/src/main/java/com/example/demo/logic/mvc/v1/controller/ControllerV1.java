package com.example.demo.logic.mvc.v1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {
    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
