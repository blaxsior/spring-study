package com.blaxsior.demo.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {
    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
// 로직의 일관성 & 다형성 목적