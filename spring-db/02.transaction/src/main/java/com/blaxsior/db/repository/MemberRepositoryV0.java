package com.blaxsior.db.repository;

import com.blaxsior.db.connection.DBConnectionUtil;
import com.blaxsior.db.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
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
            throw e;
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
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
            throw e;
        } finally {
            close(con, pmst, rs);
        }
    }


    public void update(String memberId, int money) throws SQLException {
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
            throw e;
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }


    public void deleteById(String memberId) throws SQLException {
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
            throw e;
        } finally {
            // 항상 호출이 되어야 함
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        // 리소스 정리는 역순!
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if(stmt != null) {
            try{
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
