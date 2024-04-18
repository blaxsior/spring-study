package com.blaxsior.demo.frontcontroller.v5;

import com.blaxsior.demo.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerAdapter {
    /**
     * 타겟 컨트롤러 지원 여부를 반환하는 메서드
     * @param target 타겟 컨트롤러
     * @return 컨트롤러 지원 여부
     */
    boolean support(Object target);
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object target) throws ServletException, IOException;
}
