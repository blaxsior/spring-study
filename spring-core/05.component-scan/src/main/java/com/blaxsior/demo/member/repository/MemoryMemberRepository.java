package com.blaxsior.demo.member.repository;

import com.blaxsior.demo.member.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        this.store.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(this.store.get(id));
    }
}
