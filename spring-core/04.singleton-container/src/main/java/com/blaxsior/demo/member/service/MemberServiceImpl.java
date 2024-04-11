package com.blaxsior.demo.member.service;

import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.repository.MemberRepository;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    // 실제로 의존하는 대상은 구현체. DIP 위반.
    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        this.memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMember(Long id) {
        return this.memberRepository.findById(id);
    }
}
