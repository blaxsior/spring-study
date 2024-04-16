package com.blaxsior.demo.frontcontroller.v4;

import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberFormControllerV4;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberListControllerV4;
import com.blaxsior.demo.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 하위 모든 url을 받는다.
@WebServlet(urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
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

        Map<String, Object> model = new HashMap<>();
        Map<String, String> paramMap = createParamMap(req);

        String viewPath = controller.process(paramMap, model);

        MyView view = viewResolver(viewPath);
        // attribute 설정
        view.render(model, req, res);
    }

    private static MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }

    private static MyView viewResolver(ModelView modelView) {
        return viewResolver(modelView.getViewName());
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
