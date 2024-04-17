package com.example.demo.logic.mvcdi.v5;

import com.example.demo.logic.MyModelView;
import com.example.demo.logic.MyView;
import com.example.demo.logic.mvcdi.MyController;
import com.example.demo.logic.mvcdi.v5.adapter.ControllerAdapter;
import com.example.demo.logic.mvcdi.v5.adapter.ControllerV3Adapter;
import com.example.demo.logic.mvcdi.v5.adapter.ControllerV4Adapter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@WebServlet(urlPatterns = "/mvc/v6/*")
public class FrontControllerDi extends HttpServlet {
    private Map<String, MyController> controllerMap;
    private List<ControllerAdapter> adapters;

    @Autowired
    public FrontControllerDi(Map<String, MyController> controllerMap, List<ControllerAdapter> adapters) {
        this.controllerMap = controllerMap;
        this.adapters = adapters;
    }

    //    public void setControllerMap(Map<String, Object> controllerMap) {
//        this.controllerMap = controllerMap;
//    }
//
//    public void setAdapters(List<ControllerAdapter> adapters) {
//        this.adapters = adapters;
//    }

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
