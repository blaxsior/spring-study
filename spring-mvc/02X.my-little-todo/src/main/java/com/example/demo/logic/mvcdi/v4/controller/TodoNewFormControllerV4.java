package com.example.demo.logic.mvcdi.v4.controller;

import com.example.demo.logic.MyModel;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component("/v4/todo/new-form")
public class TodoNewFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> params, MyModel model) throws ServletException, IOException {
        return "todo/new-form";
    }
}