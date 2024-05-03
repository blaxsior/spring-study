package hello.login.web.session;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    public void create(Object value, HttpServletResponse response) {
        // 랜덤 sessionId 생성
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // 쿠키 생성
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }

    public Object get(HttpServletRequest req) {
        Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }

        var obj =  sessionStore.get(cookie.getValue());
        return obj;
    }
    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        // 대응 쿠키가 있으면 제거
        if(cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest req, String cookieName) {
        var cookies = req.getCookies();
        if(cookies == null) {
            return null;
        }
        // 병렬로 찾아 발견하는 쿠키 중 어떤 쿠키를 반환
        return Arrays.stream(cookies)
                .filter(it -> it.getName().equals(cookieName))
                .findAny().orElse(null);
    }
}
