package com.blaxsior.demo.frontcontroller.v2;

import com.blaxsior.demo.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
// 로직의 일관성 & 다형성 목적