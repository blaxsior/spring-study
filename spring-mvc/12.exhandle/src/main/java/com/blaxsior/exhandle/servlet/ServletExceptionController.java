package com.blaxsior.exhandle.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {
    @GetMapping("/exception-test")
    public void errorEx() {
        throw new RuntimeException("예외 발생");
    }

    @GetMapping("/error404")
    public void error404(HttpServletResponse res) throws IOException {
        res.sendError(HttpServletResponse.SC_NOT_FOUND, "404 오류");
    }

    @GetMapping("/error500")
    public void error500(HttpServletResponse res) throws IOException {
        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500 오류");
    }
}
