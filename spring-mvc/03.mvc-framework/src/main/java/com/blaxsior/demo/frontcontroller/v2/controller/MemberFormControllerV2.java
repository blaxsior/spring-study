package com.blaxsior.demo.frontcontroller.v2.controller;

import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

        var view = new MyView(viewPath);
        return view;
    }
}
