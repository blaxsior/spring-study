package com.example.demo.logic.mvc.v3.controller;

import com.example.demo.logic.MyModelView;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    MyModelView process(Map<String, String> params) throws ServletException, IOException;
}
