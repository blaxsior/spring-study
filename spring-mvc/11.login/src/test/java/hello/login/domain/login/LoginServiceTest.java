package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginServiceTest {
    static private LoginService loginService;
    static private MemberRepository memberRepository;
    @BeforeAll
    static void init() {
        memberRepository = new MemberRepository();
        loginService = new LoginService(memberRepository);

        // 기본 유저 추가
        Member member = new Member();
        member.setLoginId("test");
        member.setName("name");
        member.setPassword("test!");

        memberRepository.save(member);

    }

    @Test
    @DisplayName("로그인 성공하면 유저 반환")
    void loginSuccess() {
        var loginId = "test";
        var password = "test!";

        var user = loginService.login(loginId, password);

        Assertions.assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("loginId에 맞는 유저 없으면 null 반환")
    void noUserMatch() {
        var loginId = "invalid";
        var password = "test!";

        var user = loginService.login(loginId, password);

        Assertions.assertThat(user).isNull();
    }


    @Test
    @DisplayName("loginId에 맞는 유저 있지만 비밀번호 안맞으면 null 반환")
    void loginIdWithWrongPassword() {
        var loginId = "test";
        var password = "invalid";

        var user = loginService.login(loginId, password);

        Assertions.assertThat(user).isNull();
    }
}