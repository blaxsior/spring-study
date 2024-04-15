package com.study.blaxsior.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="requestParamServlet",  urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println("[전체 파라미터]");

        var params = req.getParameterNames();
        params.asIterator().forEachRemaining(it -> System.out.println(it + " " + req.getParameter(it)));

        System.out.println("[단일 파라미터]");

        String username = req.getParameter("username");
        System.out.println("username = " + username);
        String age = req.getParameter("age");
        System.out.println("age = " + age);
        // 여러 값 가져오기
        String[] usernames = req.getParameterValues("username");
            for(var uname: usernames) {
            System.out.println("username = " + uname);
        }
    }
}
