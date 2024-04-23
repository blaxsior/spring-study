package com.blaxsior.db.service;

import com.blaxsior.db.domain.Member;
import com.blaxsior.db.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
/**
 * 트랜잭션 매니저 사용
 * DatasourceUtils
 */
public class MemberServiceV3_1 {
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_1(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.transactionManager = transactionManager;
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            // 비즈니스 로직 수행
            bizLogic(fromId, toId, money);
            // 성공 => 커밋
            transactionManager.commit(status);
        } catch (Exception e) {
            // 실패 => 롤백
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }
        // release 과정이 필요 X. 내부적으로 커밋 또는 롤백 시 커넥션을 종료
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
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
