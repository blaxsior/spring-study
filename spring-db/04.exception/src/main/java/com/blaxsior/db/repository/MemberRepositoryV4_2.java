package com.blaxsior.db.repository;

import com.blaxsior.db.domain.Member;
import com.blaxsior.db.repository.ex.MyDBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
/**
 * checked 예외를 MyDBException 예외로 변환
 */
public class MemberRepositoryV4_2 implements  MemberRepository {
    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;
    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
    }

    public Member save(Member member) {
        //  파라미터 바인딩 위해 ? 기호 사용
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null; // 파라미터 바인딩 가능한 Statement

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            var count = pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw this.exTranslator.translate("save", sql, e);
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pmst= null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pmst = con.prepareStatement(sql);
            pmst.setString(1, memberId);
            rs = pmst.executeQuery();
            if(rs.next()) { // 처음에는 아무데도 안가리킴
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberid = " + memberId);
            }
        } catch(SQLException e) {
            log.error("db error", e);
            throw this.exTranslator.translate("findById", sql, e);
        } finally {
            close(con, pmst, rs);
        }
    }


    public void update(String memberId, int money) {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null; // 파라미터 바인딩 가능한 Statement

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            var count = pstmt.executeUpdate();
            log.info("size = {}", count);
        } catch (SQLException e) {
            log.error("db error", e);
            throw this.exTranslator.translate("update", sql, e);
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }


    public void deleteById(String memberId) {
        String sql = "delete member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null; // 파라미터 바인딩 가능한 Statement

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            var count = pstmt.executeUpdate();
            log.info("size = {}", count);
        } catch (SQLException e) {
            log.error("db error", e);
            throw this.exTranslator.translate("delete", sql, e);
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        // 트랜잭션 동기화 시 DatasourceUtils를 사용해야 함.
        // 트랜잭션 동기화 매니저가 관리하는 데이터면 커넥션 유지, 아니면 닫음
        DataSourceUtils.releaseConnection(con, this.dataSource);
//        JdbcUtils.closeConnection(con);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeResultSet(rs);
    }

    private Connection getConnection() throws SQLException {
        // 트랜잭션 동기화를 위해서는 DataSourceUtils를 사용해야 함
        // DataSourceUtils = 트랜잭션 매니저는 외부 datasource를 이용하여 커넥션 생성
        // 내부적으로 TransactionSynchronizationManager을 이용, 커넥션을 저장
        // 나중에 커넥션이 필요하면 트랜잭션 동기화 매니저에 보관된 커넥션을 꺼내 사용
        Connection connection = DataSourceUtils.getConnection(this.dataSource);
        return connection;
    }
}
