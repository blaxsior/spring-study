package com.blaxsior.db.service;

import com.blaxsior.db.domain.Member;
import com.blaxsior.db.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 스프링부트 빈 자동 등록(DataSource, PlatformTransactionManager)
 */
@SpringBootTest // 스프링 컨테이너 - 빈 등록 기능 사용. AOP 사용하려면 스프링 빈 등록 필요.
class MemberServiceV4Test {
    static final String MEMBER_A = "memberA";
    static final String MEMBER_B = "memberB";
    static final String MEMBER_EX = "ex";

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceV4 memberService;

    @TestConfiguration
    static class AppConfig {
//        private final DataSource dataSource;
//        private final TransactionManager transactionManager;
//        @Autowired
//        AppConfig(DataSource dataSource, TransactionManager transactionManager) {
//            this.dataSource = dataSource;
//            this.transactionManager = transactionManager;
//        }
        // 생략해도 동작
        @Bean
        MemberRepository memberRepository(DataSource dataSource) {
//            var memberRepository = new MemberRepositoryV4_2(dataSource);
            var memberRepository = new MemberRepositoryV5(dataSource);
            return memberRepository;
        }

        @Bean
        MemberServiceV4 memberService(MemberRepository memberRepository) {
            var memberService = new MemberServiceV4(memberRepository);
            return memberService;
        }
    }

    @AfterEach
    void afterEach() throws SQLException {
        memberRepository.deleteById(MEMBER_A);
        memberRepository.deleteById(MEMBER_B);
        memberRepository.deleteById(MEMBER_EX);
    }

    @Test
    void aopCheck() {
        System.out.println("memberService =" + memberService.getClass());
        System.out.println("memberRepository =" + memberRepository.getClass());
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        var memberA = new Member(MEMBER_A, 10000);
        var memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        Member findMemberA = memberRepository.findById(MEMBER_A);
        assertThat(findMemberA.getMoney()).isEqualTo(8000);

        Member findMemberB = memberRepository.findById(MEMBER_B);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생하면 롤백")
    void accountTransferEx() throws SQLException {
        var memberA = new Member(MEMBER_A, 10000);
        var memberEx = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);

        assertThatThrownBy(() -> {
            memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 2000);
        }).isInstanceOf(IllegalStateException.class);

        Member findMemberA = memberRepository.findById(MEMBER_A);
        Member findMemberB = memberRepository.findById(MEMBER_EX);
        // 예외 발생 시 롤백된다.
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }
}