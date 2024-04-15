package com.study.blaxsior.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blaxsior.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletInputStream stream = req.getInputStream();
        String payload = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);

        HelloData helloData = this.mapper.readValue(payload, HelloData.class);
        System.out.println("helloData = " + helloData);
    }
}
