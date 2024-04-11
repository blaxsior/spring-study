package com.blaxsior.demo.member.repository;

import com.blaxsior.demo.member.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Long id);
}
