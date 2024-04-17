package com.example.demo.logic.mvc.v3.controller;

import com.example.demo.logic.mvc.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class TodoNewFormControllerV3 implements ControllerV3 {
    @Override
    public MyModelView process(Map<String, String> params) throws ServletException, IOException {
        return new MyModelView("/todo/new-form");
    }
}