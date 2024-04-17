package com.example.demo.logic.mvcdi.v3.controller;

import com.example.demo.logic.MyModelView;
import com.example.demo.logic.mvcdi.MyController;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 extends MyController {
    MyModelView process(Map<String, String> params) throws ServletException, IOException;
}
