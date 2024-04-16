package com.blaxsior.demo.servlet.domain.member;

import com.blaxsior.demo.domain.member.Member;
import com.blaxsior.demo.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // Arrange
        Member member = new Member("test", 10);
        Member expected = memberRepository.save(member);

        // Act
        Member target = memberRepository.findById(expected.getId());

        // Assert
        assertThat(target).isSameAs(expected);
    }

    @Test
    void findAll() {
        //Arrange
        Member m1 = new Member("test1", 10);
        Member m2 = new Member("test2", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);
        // Act
        List<Member> members = memberRepository.findAll();

        // Assertions
        assertThat(members.size()).isEqualTo(2);
    }
}