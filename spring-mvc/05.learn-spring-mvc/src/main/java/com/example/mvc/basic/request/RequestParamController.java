package com.example.mvc.basic.request;

import com.example.mvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("username={}, age={}", username, age);

        res.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody // 반환값을 HTTP 응답 메시지로 취급
    public String requestParam2(@RequestParam("username") String username, @RequestParam("age") Integer age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody // 이름 같으면 생략 가능
    public String requestParam3(@RequestParam String username, @RequestParam Integer age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody // 이름 같으면 어노테이션도 생략 가능
    public String requestParam4(String username, Integer age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }
    
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age // int면 오류남!
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, defaultValue = "-1") int age // int 가능
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(
            @RequestParam MultiValueMap<String, Object> paramMap
            ) {
        log.info("username={}, age={}",
                paramMap.get("username"),
                paramMap.get("age")
        );
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(
            @RequestParam("username") String username,
            @RequestParam("age") Integer age
    ) {
        HelloData data = new HelloData();
        data.setUsername(username);
        data.setAge(age);
        log.info("user={}", data);

        return "ok";
    }

    @RequestMapping("/model-attribute-v2")
    @ResponseBody
    public String modelAttributeV1(
            @ModelAttribute HelloData data
    ) {
        log.info("user={}", data);

        return "ok";
    }
}
