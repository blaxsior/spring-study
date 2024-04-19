package com.example.mvc.basic.response;

import com.example.mvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyv1(HttpServletResponse res) throws IOException {
        res.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyv2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/response-body-string-v3")
    @ResponseBody
    public String responseBodyv3(HttpServletResponse res) {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    @ResponseBody
    public ResponseEntity<HelloData> responseBodyJsonv1() {
        HelloData data = new HelloData();
        data.setUsername("test");
        data.setAge(30);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/response-body-json-v2")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public HelloData responseBodyJsonv2() {
        HelloData data = new HelloData();
        data.setUsername("test");
        data.setAge(30);

        return data;
    }
}
