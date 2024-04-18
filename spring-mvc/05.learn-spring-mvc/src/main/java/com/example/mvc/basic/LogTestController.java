package com.example.mvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String message = "log test";

        // 이전까지 사용하던 방식
        System.out.println(message);
        // 로그 라이브러리 사용
        log.trace("trace log = {}", message);
        log.debug("debug log = {}", message);
        log.info("info log = {}", message);
        log.warn("warn log = {}", message);
        log.error("error log = {}", message);

        return "ok";
    }
}
