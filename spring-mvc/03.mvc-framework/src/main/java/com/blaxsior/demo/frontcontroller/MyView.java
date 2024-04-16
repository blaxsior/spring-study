package com.blaxsior.demo.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(this.viewPath);
        dispatcher.forward(req, res);
    }

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        modelToRequestAttribute(model, req);
        this.render(req, res);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((k, v) -> req.setAttribute(k, v));
    }
}
