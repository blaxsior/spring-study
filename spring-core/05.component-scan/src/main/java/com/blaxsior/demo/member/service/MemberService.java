package com.blaxsior.demo.member.service;

import com.blaxsior.demo.member.Member;

import java.util.Optional;

public interface MemberService {
    void join(Member member);
    Optional<Member> findMember(Long id);
}
