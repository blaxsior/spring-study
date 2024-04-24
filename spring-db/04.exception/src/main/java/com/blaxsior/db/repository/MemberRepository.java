package com.blaxsior.db.repository;

import com.blaxsior.db.domain.Member;


public interface MemberRepository {
    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void deleteById(String memberId);
}
