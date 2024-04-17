package com.example.demo.logic.mvcdi.v3.controller;

import com.example.demo.logic.MyModelView;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component("/v3/todo/new-form")
public class TodoNewFormControllerV3 implements ControllerV3 {
    @Override
    public MyModelView process(Map<String, String> params) throws ServletException, IOException {
        return new MyModelView("/todo/new-form");
    }
}