package com.example.demo.logic.mvc.v4.controller;

import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public class TodoNewFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> params, MyModel model) throws ServletException, IOException {
        return "todo/new-form";
    }
}