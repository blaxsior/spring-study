package com.blaxsior.db.service;

import com.blaxsior.db.domain.Member;
import com.blaxsior.db.repository.MemberRepositoryV2;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MemberServiceV2 {
    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public MemberServiceV2(DataSource dataSource, MemberRepositoryV2 memberRepository) {
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);
            // 비즈니스 로직 수행
            bizLogic(fromId, toId, money, con);
            // 성공 => 커밋
            con.commit();
        } catch (Exception e) {
            // 실패 => 롤백
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(con);
        }
    }

    private void bizLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null) {
            try {
                con.setAutoCommit(true); // 커밋 상태를 다시 돌려줌
                con.close();
            } catch (Exception e) {
                log.error("exception = ", e);
            }
        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("계좌 중 예외 발생");
        }
    }
}
