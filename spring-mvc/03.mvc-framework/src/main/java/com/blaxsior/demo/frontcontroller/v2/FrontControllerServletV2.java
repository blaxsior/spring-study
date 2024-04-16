package com.blaxsior.demo.frontcontroller.v2;

import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v2.controller.MemberFormControllerV2;
import com.blaxsior.demo.frontcontroller.v2.controller.MemberListControllerV2;
import com.blaxsior.demo.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 하위 모든 url을 받는다.
@WebServlet(urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        var requestUrl = req.getRequestURI();
        var controller = controllerMap.get(requestUrl);

        if(controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 다형성 활용
        MyView view = controller.process(req, res);
        view.render(req, res);
    }
}
