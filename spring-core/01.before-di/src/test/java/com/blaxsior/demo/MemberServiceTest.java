package com.blaxsior.demo;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join() {
        //arrange
        var memberId = 1L;
        var member = new Member(memberId, "test-member", Grade.VIP);

        //act
        memberService.join(member);

        var member2 = memberService.findMember(memberId);

        //assert
        Assertions.assertEquals(member, member2);
    }
}
