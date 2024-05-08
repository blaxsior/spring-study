package com.blaxsior.exhandle.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        var uuid = UUID.randomUUID().toString();
        var requestUri = request.getRequestURI();
        var dispatcherType = request.getDispatcherType();

        try {
            log.info("[LogFilter] uuid = [{}] URI = [{}] dispatcherType = [{}]", uuid, requestUri, dispatcherType);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch(Exception e) {
            throw e;
        } finally {
            log.info("[LogFilter] uuid = {} finished", uuid);
        }
    }
}
