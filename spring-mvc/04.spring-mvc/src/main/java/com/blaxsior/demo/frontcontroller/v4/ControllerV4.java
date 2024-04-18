package com.blaxsior.demo.frontcontroller.v4;

import com.blaxsior.demo.frontcontroller.ModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV4 {
    String process(Map<String, String> paramMap, Map<String, Object> model) throws ServletException, IOException;
}
// 로직의 일관성 & 다형성 목적