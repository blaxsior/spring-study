package com.blaxsior.exhandle.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/error-page")
public class ErrorPageController {
    // RequestDispatcher에 상수로 정의
    String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
    String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
    String ERROR_MESSAGE = "jakarta.servlet.error.message";
    String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
    String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
    String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";
    @GetMapping("/404")
    public String error404(HttpServletRequest request, HttpServletResponse response) {
        log.info("error 404");
        printErrorInfo(request);
        return "error-page/404";
    }

    @GetMapping(value= "/404", produces = "application/json")
    public ResponseEntity<Map<String, Object>> error404Json(HttpServletRequest request, HttpServletResponse response) {
        log.info("error 404 json");
        printErrorInfo(request);
        Map<String, Object> result = new HashMap<>();

        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        result.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return ResponseEntity
                .status(statusCode)
                .body(result);
    }

    @GetMapping(value = "/500", produces = "application/json")
    public ResponseEntity<Map<String, Object>> error500Json(HttpServletRequest request, HttpServletResponse response) {
        log.info("error 500 json");
        printErrorInfo(request);
        Map<String, Object> result = new HashMap<>();

        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        result.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return ResponseEntity
                .status(statusCode)
                .body(result);
    }

    private void printErrorInfo(HttpServletRequest request) {
        log.info("ERROR_EXCEPTION={}",request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE={}",request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE={}",request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI={}",request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME={}",request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE={}",request.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatcherType={}", request.getDispatcherType());
    }
}
