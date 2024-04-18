package com.blaxsior.demo.frontcontroller.v3;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap) throws ServletException, IOException;
}
// 로직의 일관성 & 다형성 목적