package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionConst;
import hello.login.web.session.SessionManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    //    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }

        log.info("loginForm = {}", loginForm);

        Member member = loginService.login(
                loginForm.loginId(),
                loginForm.password()
        );

        log.info("member = {}", member);

        if (member == null) {
            result.reject("loginFail", "아이디 또는 패스워드가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 시 처리

        Cookie idCookie = new Cookie("memberId", member.getId().toString());
        response.addCookie(idCookie);

        return "redirect:/";
    }

    //    @PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }

        log.info("loginForm = {}", loginForm);

        Member member = loginService.login(
                loginForm.loginId(),
                loginForm.password()
        );

        log.info("member = {}", member);

        if (member == null) {
            result.reject("loginFail", "아이디 또는 패스워드가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 시 세션 생성
        sessionManager.create(member, response);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }

        Member member = loginService.login(
                loginForm.loginId(),
                loginForm.password()
        );

        if (member == null) {
            result.reject("loginFail", "아이디 또는 패스워드가 맞지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV4(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                          BindingResult result,
                          HttpServletRequest request,
                          @RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL
    ) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }

        Member member = loginService.login(
                loginForm.loginId(),
                loginForm.password()
        );

        if (member == null) {
            result.reject("loginFail", "아이디 또는 패스워드가 맞지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "redirect:" + redirectURL;
    }


    //    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");

        return "redirect:/";
    }


//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute(SessionConst.LOGIN_MEMBER);
//            session.invalidate();
        }
        return "redirect:/";
    }

    private static void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie idCookie = new Cookie(cookieName, null);
        idCookie.setMaxAge(0);
        response.addCookie(idCookie);
    }
}
