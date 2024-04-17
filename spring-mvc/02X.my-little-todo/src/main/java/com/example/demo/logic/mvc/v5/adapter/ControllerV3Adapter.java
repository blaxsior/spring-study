package com.example.demo.logic.mvc.v5.adapter;

import com.example.demo.logic.mvc.MyModel;
import com.example.demo.logic.mvc.MyModelView;
import com.example.demo.logic.mvc.v3.controller.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3Adapter implements ControllerAdapter {
    @Override
    public boolean support(Object target) {
        return target instanceof ControllerV3;
    }

    @Override
    public MyModelView handle(Object target, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!support(target)) {
            throw new IllegalArgumentException("target controller not supported by ControllerV3Adapter, name = " + target.getClass().getSimpleName());
        }

        ControllerV3 controller = (ControllerV3) target;

        Map<String, String> params = getParamMap(req);
        MyModelView modelView = controller.process(params);

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
