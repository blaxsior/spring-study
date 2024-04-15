package com.study.blaxsior.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BufferedReader reader = req.getReader(); // 버퍼 리더 방식

        String[] lines = reader.lines().toArray(String[]::new);
        for(var line: lines) {
            System.out.println(line);
        }

        ServletInputStream stream = req.getInputStream();

        // 스프링에서 지원하는 스트림 => 문자열 유틸리티
        String payload = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        System.out.println("payload = " + payload);
    }
}
