package com.blaxsior.demo.member.service;

import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

    @Autowired
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
