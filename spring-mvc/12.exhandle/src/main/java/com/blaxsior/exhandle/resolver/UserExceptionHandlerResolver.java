package com.blaxsior.exhandle.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserExceptionHandlerResolver implements HandlerExceptionResolver {
    private final ObjectMapper objMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if(ex instanceof UserException) {
                log.info("User Exception");
                String accept = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(accept)) {
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());
                    // Map을 JSON 텍스트로 변환
                    String errorBody = objMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(errorBody);
                    // 아무것도 반환 X. 정상 흐름으로
                    return new ModelAndView();
                } else {
                    // 다른 뷰를 표현
                    return new ModelAndView("error/500");
                }
            }
        } catch(Exception e) {
            log.error("exception: {}", e.getStackTrace());
        }

        return null;
    }
}
