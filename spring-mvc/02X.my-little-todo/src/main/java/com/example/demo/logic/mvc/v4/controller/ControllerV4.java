package com.example.demo.logic.mvc.v4.controller;

import com.example.demo.logic.MyModel;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV4 {
    String process(Map<String, String> params, MyModel model) throws ServletException, IOException;
}
