package com.study.blaxsior.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// name은 필수 X. 아마 name 없으면 클래스 이름 기반으로 설정할 것으로 보임
@WebServlet(urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // status 지정
        res.setStatus(HttpServletResponse.SC_OK);

        // encoding 지정
        res.setCharacterEncoding("utf-8");

        // header 지정
        res.setHeader("Content-Type", "text/plain");
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        // 커스텀 헤더도 가능. but 권장하는 사항은 아니다.
        res.setHeader("my-header", "my-header-value");

        // Set-Cookie:로 직접 넣는것도 가능
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        res.addCookie(cookie);

//        // redirect도 가능
//        res.sendRedirect("/hello-form.html");
        res.getWriter().write("message");
    }

    private void redirect(HttpServletResponse res) throws IOException {
        // 이렇게 해도 되지만
//        res.setStatus(HttpServletResponse.SC_FOUND);
//        res.setHeader("Location", "/basic/hello-form.html");
        // 메서드 제공
        res.sendRedirect("/hello-form.html");
    }
}
