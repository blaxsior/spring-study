package com.blaxsior.demo;

import com.blaxsior.demo.core.AppConfig;
import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.repository.MemoryMemberRepository;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberService = new MemberServiceImpl(new MemoryMemberRepository());
    }

    @Test
    void join() {
        //arrange
        var memberId = 1L;
        var member = new Member(memberId, "test-member", Grade.VIP);

        //act
        memberService.join(member);
        var member2 = memberService.findMember(memberId).get();

        //assert
        Assertions.assertEquals(member, member2);
    }
}
