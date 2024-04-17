package com.example.demo.logic.mvc.v1;

import com.example.demo.logic.mvc.v1.controller.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/mvc/v1/*")
public class FrontControllerV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap.put("/todo", new TodoListController());
        controllerMap.put("/todo/new-form", new TodoNewFormController());
        controllerMap.put("/todo/create", new TodoSuccessController());
        controllerMap.put("/todo/delete", new TodoDeleteController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var pathInfo = getPathInfo(req);

        // 컨트롤러 획득 및 존재 검사
        ControllerV1 controller = controllerMap.get(pathInfo);
        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(req, res);
    }

    private static String getPathInfo(HttpServletRequest req) {
        var pathInfo = Objects.requireNonNullElseGet(req.getPathInfo(), () -> "");
        System.out.println("pathInfo = " + pathInfo);

        return pathInfo;
    }
}
