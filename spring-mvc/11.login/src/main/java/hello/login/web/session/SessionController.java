package hello.login.web.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@Controller
@RestController
public class SessionController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다";
        }

        // 세션 목록 출력
        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(
                        name -> {
                            log.info("session name = {}, value = {}", name, session.getAttribute(name));
                        }
                );
        log.info("sessionId={}", session.getId());
        log.info("maxinactiveinterval = {}", session.getMaxInactiveInterval());
        log.info("creationtime={}", new Date(session.getCreationTime()));
        log.info("last access = {}", new Date(session.getLastAccessedTime()));
        log.info("is new = {}", session.isNew());

        return "성공";
    }
}
