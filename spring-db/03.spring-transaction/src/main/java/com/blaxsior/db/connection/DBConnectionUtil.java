package com.blaxsior.db.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.blaxsior.db.connection.ConnectionConst.*;
@Slf4j
public class DBConnectionUtil {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("connection = {}, class = {}",connection, connection.getClass());
            return connection;
        } catch(SQLException e) {
            // checked exception을 runtime exception으로 바꿔 반환
            throw new IllegalStateException(e);
        }
    }
}
