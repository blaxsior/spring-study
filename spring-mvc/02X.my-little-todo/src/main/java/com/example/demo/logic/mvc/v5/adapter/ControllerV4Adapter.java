package com.example.demo.logic.mvc.v5.adapter;

import com.example.demo.logic.MyModel;
import com.example.demo.logic.MyModelView;
import com.example.demo.logic.mvc.v4.controller.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4Adapter implements ControllerAdapter {
    @Override
    public boolean support(Object target) {
        return target instanceof ControllerV4;
    }

    @Override
    public MyModelView handle(Object target, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!support(target)) {
            throw new IllegalArgumentException("target controller not supported by ControllerV4Adapter, name = " + target.getClass().getSimpleName());
        }

        ControllerV4 controller = (ControllerV4) target;

        Map<String, String> params = getParamMap(req);
        MyModel model = new MyModel();
        String path = controller.process(params, model);

        MyModelView modelView = new MyModelView(path);
        modelView.setModel(model);

        return modelView;
    }

    private static Map<String, String> getParamMap(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                key-> params.put(key, req.getParameter(key))
        );
        return params;
    }
}
