package com.blaxsior.demo.frontcontroller.v5.adapter;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.v3.ControllerV3;
import com.blaxsior.demo.frontcontroller.v5.ControllerAdapter;
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
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object target) throws ServletException, IOException {
        if(!support(target)) throw new IllegalArgumentException("not supported. target isn't instance of ControllerV3");

        ControllerV3 controller = (ControllerV3) target;

        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        return modelView;
    }

    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        var parameters = req.getParameterNames();

        parameters.asIterator().forEachRemaining(
                param -> paramMap.put(param, req.getParameter(param))
        );
        return paramMap;
    }
}
