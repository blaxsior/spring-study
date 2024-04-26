package com.blaxsior.thymeleaf.basic;

import com.blaxsior.thymeleaf.basic.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "hello, spring thymeleaf!");
        return "basic/text-basic"; // 반환값 void, 파라미터에 결과 설정 가능 옵션 X이면 생략 가능하긴 함.
    }

    @GetMapping("text-unescaped")
    public String unescaped(Model model) {
        model.addAttribute("data", "hello, spring thymeleaf!");
        return "basic/unescaped"; // 반환값 void, 파라미터에 결과 설정 가능 옵션 X이면 생략 가능하긴 함.
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("request", request);
//        model.addAttribute("response", response);
//        model.addAttribute("servletContext", request.getServletContext());
        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("servletContext = {}", request.getServletConnection());

        session.setAttribute("sessionData", "hello session");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }


    @Component("helloBean")
    static class HelloBean {
        public String hello() {
            return "hello, Bean!";
        }
    }
}
