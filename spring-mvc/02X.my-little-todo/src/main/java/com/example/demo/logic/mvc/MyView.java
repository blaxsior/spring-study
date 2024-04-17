package com.example.demo.logic.mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MyView {
    private String path;

    public MyView(String path) {
        this.path = path;
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(this.path);
        dispatcher.forward(req, res);
    }

    public void render(MyModel model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<String> keys = model.getAttributeNames();
        keys.forEach(key -> req.setAttribute(key, model.getAttribute(key)));

        RequestDispatcher dispatcher = req.getRequestDispatcher(this.path);
        dispatcher.forward(req, res);
    }
}
