package com.blaxsior.exhandle.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    // 직접 넘길 수 없으므로 간접적으로 넘김
    private static final String LOG_ID = "LOG_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String logId = UUID.randomUUID().toString();
        var dispatcherType = request.getDispatcherType();
        request.setAttribute(LOG_ID, logId);
        log.info("[LOG-PREHANDLE] REQUEST [{}][{}][{}][{}]", logId, dispatcherType, requestUri, handler);

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("[LOG-POSTHANDLE] ModelAndView = [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Object logId = request.getAttribute(LOG_ID);
        String requestUri = request.getRequestURI();
        log.info("[LOG-AFTERCOMPLETION] RESPONSE [{}][{}][{}]", logId, requestUri, ex);

    }
}
