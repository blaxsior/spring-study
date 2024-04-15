package com.study.blaxsior.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blaxsior.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        HelloData data = new HelloData();
        data.setName("john dow");
        data.setAge(13);

        var result = this.mapper.writeValueAsString(data);

        res.getWriter().write(result);
    }
}
