package com.blaxsior.demo.frontcontroller.v5.adapter;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.v4.ControllerV4;
import com.blaxsior.demo.frontcontroller.v5.ControllerAdapter;
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
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object target) throws ServletException, IOException {
        if(!support(target)) throw new IllegalArgumentException("not supported. target isn't instance of ControllerV4");
        ControllerV4 controller = (ControllerV4) target;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewPath = controller.process(paramMap, model);
        ModelView modelView = new ModelView(viewPath);
        modelView.setModel(model);

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
