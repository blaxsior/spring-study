package com.blaxsior.db.repository;

import com.blaxsior.db.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void save() throws SQLException {
        var member = new Member("member1", 10000);
        repository.save(member);
    }

    @Test
    void findById() throws SQLException {
        Member member = new Member("member3", 20000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemberId());
        System.out.println("findMember = " + findMember);
        // isEqualTo는 내부 값을 비교. 두 객체는 다른데 객체 값은 같다.
        // lombok equalsandhashcode 덕분에 가능
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void update() throws SQLException {
        Member member = new Member("member4", 20000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemberId());
        System.out.println("findMember = " + findMember);
        // isEqualTo는 내부 값을 비교. 두 객체는 다른데 객체 값은 같다.
        // lombok equalsandhashcode 덕분에 가능
        assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemberId(), 20000);
        var updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);
    }

    @Test
    void delete() throws SQLException {
        Member member = new Member("member5", 20000);
        repository.save(member);
        repository.deleteById(member.getMemberId());

        // 데이터 제거, 예외 발생
        assertThatThrownBy(
                () -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}