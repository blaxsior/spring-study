package com.example.demo.logic.mvc.v4;

import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.logic.mvc.MyView;
import com.example.demo.logic.mvc.v4.controller.*;
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

@WebServlet(urlPatterns = "/mvc/v4/*")
public class FrontControllerV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMap.put("/todo", new TodoListControllerV4());
        controllerMap.put("/todo/new-form", new TodoNewFormControllerV4());
        controllerMap.put("/todo/create", new TodoSuccessControllerV4());
        controllerMap.put("/todo/delete", new TodoDeleteControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var pathInfo = getPathInfo(req);

        // 컨트롤러 획득 및 존재 검사
        ControllerV4 controller = controllerMap.get(pathInfo);
        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> params = getParamMap(req);
        MyModel model = new MyModel();
        String path = controller.process(params, model);

        MyModelView mv = new MyModelView(path);
        mv.setModel(model);

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
