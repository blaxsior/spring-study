package com.blaxsior.demo.frontcontroller.v5;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberFormControllerV3;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberListControllerV3;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberFormControllerV4;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberListControllerV4;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.blaxsior.demo.frontcontroller.v5.adapter.ControllerV3Adapter;
import com.blaxsior.demo.frontcontroller.v5.adapter.ControllerV4Adapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 하위 모든 url을 받는다.
@WebServlet(urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();
    private List<ControllerAdapter> adapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initControllers();
        initAdapters();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service");
        var controller = getController(req);

        if (controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        ControllerAdapter adapter = getControllerAdapter(controller);

        ModelView modelView = adapter.handle(req, res, controller);

        MyView view = viewResolver(modelView);
        // attribute 설정
        view.render(modelView.getModel(), req, res);
    }

    private Object getController(HttpServletRequest req) {
        var requestUrl = req.getRequestURI();
        var controller = controllerMap.get(requestUrl);
        return controller;
    }

    private ControllerAdapter getControllerAdapter(Object controller) {
        for(var adapter: this.adapters) {
            if(adapter.support(controller)) {
                return adapter;
            }
        }
        // 적절한 어댑터 못찾으면 예외 던짐
        throw new IllegalArgumentException("cannot find adapter which supports " + controller.getClass().getName());
    }

    private void initAdapters() {
        adapters.add(new ControllerV3Adapter());
        adapters.add(new ControllerV4Adapter());
    }

    private void initControllers() {
        controllerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        controllerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private MyView viewResolver(ModelView modelView) {
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
    }
}
