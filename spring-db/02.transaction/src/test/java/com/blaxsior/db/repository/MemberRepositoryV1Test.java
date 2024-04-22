package com.blaxsior.db.repository;

import com.blaxsior.db.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.blaxsior.db.connection.ConnectionConst.*;

class MemberRepositoryV1Test {
    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        //기본 DriverManager. 항상 새로운 커넥션 획득 (스레드 풀 아님)
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        // 커넥션 풀 사용
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

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