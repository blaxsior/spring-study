package hello.login.web.session;

import hello.login.domain.member.Member;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    @DisplayName("세션 생성")
    void sessionTest() {
        // httpservletmock이 필요

        // 세션 생성
        MockHttpServletResponse res = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.create(member, res);

        // 요청에 응답 쿠키 저장. 응답의 쿠키 모두 꺼내서 저장
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCookies(res.getCookies());

        // 세션 조회
        Object result = sessionManager.get(req);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(req);

        Object expired = sessionManager.get(req);
        assertThat(expired).isNull();
    }
}