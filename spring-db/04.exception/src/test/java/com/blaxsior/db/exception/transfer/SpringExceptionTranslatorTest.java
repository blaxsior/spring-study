package com.blaxsior.db.exception.transfer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.blaxsior.db.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SpringExceptionTranslatorTest {

    DataSource datasource;

    @BeforeEach
    void beforeEach() {
        datasource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    @Test
    void sqlExceptionErrorCode() {
        String sql = "select bad sql"; // 문법 오류

        try {
            Connection con = datasource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch (SQLException e) {
            // 문법 오류 코드는 42122
            assertThat(e.getErrorCode()).isEqualTo(42122);
        }
    }

    @Test
    void exceptionTranslator() {
        String sql = "select bad sql"; // 문법 오류

        try {
            Connection con = datasource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch (SQLException e) {
            // 문법 오류 코드는 42122
            assertThat(e.getErrorCode()).isEqualTo(42122);
            var exTranslator = new SQLErrorCodeSQLExceptionTranslator(datasource);
            DataAccessException resultEx = exTranslator.translate("select", sql, e);
            assertThat(resultEx).isInstanceOf(BadSqlGrammarException.class);
        }
    }
}
