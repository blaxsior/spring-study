package com.example.demo.logic.mvc.v2;

import com.example.demo.logic.mvc.MyView;
import com.example.demo.logic.mvc.v2.controller.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/mvc/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap.put("/todo", new TodoListControllerV2());
        controllerMap.put("/todo/new-form", new TodoNewFormControllerV2());
        controllerMap.put("/todo/create", new TodoSuccessControllerV2());
        controllerMap.put("/todo/delete", new TodoDeleteControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var pathInfo = getPathInfo(req);

        // 컨트롤러 획득 및 존재 검사
        ControllerV2 controller = controllerMap.get(pathInfo);
        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(req, res);
        view.render(req, res);
    }

    private static String getPathInfo(HttpServletRequest req) {
        var pathInfo = Objects.requireNonNullElseGet(req.getPathInfo(), () -> "");
        System.out.println("pathInfo = " + pathInfo);

        return pathInfo;
    }
}
