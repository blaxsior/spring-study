package com.example.demo.logic.mvc.v3;

import com.example.demo.logic.MyModelView;
import com.example.demo.logic.MyView;
import com.example.demo.logic.mvc.v3.controller.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/mvc/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap.put("/todo", new TodoListControllerV3());
        controllerMap.put("/todo/new-form", new TodoNewFormControllerV3());
        controllerMap.put("/todo/create", new TodoSuccessControllerV3());
        controllerMap.put("/todo/delete", new TodoDeleteControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var pathInfo = getPathInfo(req);

        // 컨트롤러 획득 및 존재 검사
        ControllerV3 controller = controllerMap.get(pathInfo);
        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> params = getParamMap(req);
        MyModelView mv = controller.process(params);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(),req, res);
    }

    private static Map<String, String> getParamMap(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                key-> params.put(key, req.getParameter(key))
        );
        return params;
    }

    private MyView viewResolver(MyModelView mv) {
        // ModelView에 상대 경로 (hello/world)를 넣더라도 정상 동작하기 위한 가공 과정.
        // 역슬래시로 표현된 경로를 RequestDispatcher에서 제대로 처리하지 않아서 슬래시로 변경
        String path = (Paths.get("/WEB-INF/views", mv.getPath()) +  ".jsp").replace('\\', '/');
        
        MyView view = new MyView(path);
        return view;
    }

    private static String getPathInfo(HttpServletRequest req) {
        var pathInfo = Objects.requireNonNullElseGet(req.getPathInfo(), () -> "");

        return pathInfo;
    }
}
