package com.blaxsior.db.exception.transfer;

import com.blaxsior.db.connection.ConnectionConst;
import com.blaxsior.db.domain.Member;
import com.blaxsior.db.repository.ex.MyDBException;
import com.blaxsior.db.repository.ex.MyDuplicateKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.blaxsior.db.connection.ConnectionConst.*;

public class ExTranslatorV1Test {

    Repository repository;
    Service service;

    @BeforeEach
    void init() {
        var dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void dupKeySave() {
        service.create("test");
        service.create("test");
    }

    static class Repository {
        private final DataSource dataSource;

        Repository(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        public Member save(Member member) {
            String sql = "insert into member(member_id, money) values(?, ?)";
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                con = this.dataSource.getConnection();
                stmt = con.prepareStatement(sql);
                stmt.setString(1, member.getMemberId());
                stmt.setInt(2, member.getMoney());
                stmt.executeUpdate(); // 쿼리문 실행
                return member;
            } catch (SQLException e) {
                if(e.getErrorCode() == 23505) {
                    throw new MyDuplicateKeyException(e);
                }
                throw new MyDBException(e);
            } finally {
                JdbcUtils.closeStatement(stmt);
                JdbcUtils.closeConnection(con);
            }

        }
    }

    static class Service {
        private final Repository repository;

        Service(Repository repository) {
            this.repository = repository;
        }

        public void create(String memberId) {
            try {
                repository.save(new Member(memberId, 0));
            } catch (MyDuplicateKeyException e) {
                System.out.println("MyDBException");
                // 복구할 수 있는 예외
                repository.save(new Member(generateNewId(memberId), 0));
            } catch (MyDBException e) {
                // 복구 불가능한 예외
                System.out.println("MyDBException");
                throw e;
            }
        }

        private String generateNewId(String memberId) {
            return memberId + new Random().nextInt(10000);
        }
    }
}
