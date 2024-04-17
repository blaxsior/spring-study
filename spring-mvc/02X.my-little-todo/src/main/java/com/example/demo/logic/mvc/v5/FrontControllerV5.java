package com.example.demo.logic.mvc.v5;

import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.logic.mvc.MyView;
import com.example.demo.logic.mvc.v3.controller.TodoDeleteControllerV3;
import com.example.demo.logic.mvc.v3.controller.TodoListControllerV3;
import com.example.demo.logic.mvc.v3.controller.TodoNewFormControllerV3;
import com.example.demo.logic.mvc.v3.controller.TodoSuccessControllerV3;
import com.example.demo.logic.mvc.v4.controller.*;
import com.example.demo.logic.mvc.v5.adapter.ControllerAdapter;
import com.example.demo.logic.mvc.v5.adapter.ControllerV3Adapter;
import com.example.demo.logic.mvc.v5.adapter.ControllerV4Adapter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@WebServlet(urlPatterns = "/mvc/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();
    private List<ControllerAdapter> adapters = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        initControllers();
        initAdapters();
    }

    private void initAdapters() {
        adapters.add(new ControllerV3Adapter());
        adapters.add(new ControllerV4Adapter());
    }

    private void initControllers() {
        controllerMap.put("/v3/todo", new TodoListControllerV3());
        controllerMap.put("/v3/todo/new-form", new TodoNewFormControllerV3());
        controllerMap.put("/v3/todo/create", new TodoSuccessControllerV3());
        controllerMap.put("/v3/todo/delete", new TodoDeleteControllerV3());

        controllerMap.put("/v4/todo", new TodoListControllerV4());
        controllerMap.put("/v4/todo/new-form", new TodoNewFormControllerV4());
        controllerMap.put("/v4/todo/create", new TodoSuccessControllerV4());
        controllerMap.put("/v4/todo/delete", new TodoDeleteControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var pathInfo = getPathInfo(req);

        // 컨트롤러 획득 및 존재 검사
        Object controller = controllerMap.get(pathInfo);
        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ControllerAdapter adapter = getControllerAdapter(controller);
        MyModelView mv = adapter.handle(controller, req, res);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(),req, res);
    }

    private ControllerAdapter getControllerAdapter(Object controller) {
        for(var adapter: adapters) {
            if(adapter.support(controller)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("there is no adapter support target controller. name = " + controller.getClass().getSimpleName());
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
