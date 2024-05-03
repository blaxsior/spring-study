package hello.login.web.filter;

import hello.login.web.session.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        var requestURI = httpReq.getRequestURI();

        try {
            log.info("인증 필터 시작 {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 실행 {}", requestURI);
                HttpSession session = httpReq.getSession(false);

                // 세션 인증 X
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("인증 X {}", requestURI);

                    httpRes.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }

            filterChain.doFilter(req, res);
        } catch (Exception e) {
            // 예외를 서블릿 수준까지 전달해줘야 함
            throw e;
        }
    }

    /**
     * 화이트리스트면 인증 X
     */
    private boolean isLoginCheckPath(String requestUri) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestUri);
    }
}
