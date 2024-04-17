package com.example.demo.logic.mvcdi.v4.controller;

import com.example.demo.logic.MyModel;
import com.example.demo.logic.mvcdi.MyController;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV4 extends MyController {
    String process(Map<String, String> params, MyModel model) throws ServletException, IOException;
}
