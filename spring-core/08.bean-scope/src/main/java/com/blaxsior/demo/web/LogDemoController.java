package com.blaxsior.demo.web;

import com.blaxsior.demo.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LogDemoController {
    private final MyDemoService myDemoService;
//    private final ObjectProvider<MyLogger> loggerProvider;
    private final MyLogger logger;

    @GetMapping
    public String helloWorld() {
        return "hello!";
    }

    @GetMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        var logger = this.loggerProvider.getObject();
        String requestUrl = request.getRequestURI().toString();
        logger.setRequestUrl(requestUrl);

        logger.log("controller test");

        this.myDemoService.logic("testid");

        return requestUrl;
    }
}
