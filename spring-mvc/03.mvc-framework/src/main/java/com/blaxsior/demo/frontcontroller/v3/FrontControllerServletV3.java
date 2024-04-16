package com.blaxsior.demo.frontcontroller.v3;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberFormControllerV3;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberListControllerV3;
import com.blaxsior.demo.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 하위 모든 url을 받는다.
@WebServlet(urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        var requestUrl = req.getRequestURI();
        var controller = controllerMap.get(requestUrl);

        if (controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);

        ModelView modelView = controller.process(paramMap);

        MyView view = viewResolver(modelView);
        // attribute 설정
        view.render(modelView.getModel(), req, res);
    }

    private static MyView viewResolver(ModelView modelView) {
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
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
