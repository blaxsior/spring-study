package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        return memberOptional
                .filter(it -> it.getPassword().equals(password))
                .orElse(null);

//        if(memberOptional.isEmpty()) return null;
//
//        Member member = memberOptional.get();
//        if(member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }
    }
}
