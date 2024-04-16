package com.blaxsior.demo.frontcontroller.v4.controller;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) throws ServletException, IOException {
        // 논리적 이름을 전달한다.
        return "new-form";
    }
}
