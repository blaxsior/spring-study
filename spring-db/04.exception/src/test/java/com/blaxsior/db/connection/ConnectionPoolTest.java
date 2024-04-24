package com.blaxsior.db.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    @Test
    void dataSourceConnectionPool() throws InterruptedException, SQLException {
        // hikariCP 데이터소스
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("My Pool");

        useDatasource(dataSource);
        // 풀 스레드는 메인 스레드와 별도의 스레드에서 동작
        Thread.sleep(1000);
    }

    private void useDatasource(HikariDataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection(); // 부족하면 대기
        System.out.println("con1 = " + con1);
        System.out.println("con2 = " + con2);
        // 커넥션 사용하면 close로 풀어줘야 함.
        con1.close();
        con2.close();
    }
}
