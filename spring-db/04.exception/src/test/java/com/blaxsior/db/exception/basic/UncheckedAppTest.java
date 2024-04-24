package com.blaxsior.db.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class UncheckedAppTest {
    static class Controller {
        Service service = new Service();

        public void route() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient client = new NetworkClient();

        public void logic() throws ConnectException, SQLException {
            repository.call();
            client.call();
        }
    }

    static class RuntimeConnectionException extends RuntimeException {
        public RuntimeConnectionException(Throwable cause) {
            super(cause);
        }
    }


    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }

    static class NetworkClient {
        public void call(){
            try {
                this.runNet();
            } catch (ConnectException e) {
                throw new RuntimeConnectionException(e);
            }
        }

        public void runNet() throws ConnectException {
            throw new ConnectException("sql exception");
        }
    }

    static class Repository {
        public void call() {
            try {
                this.runSQL();
            } catch (SQLException e) {
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL() throws SQLException {
            throw new SQLException("sql exception");
        }
    }

    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> {
            controller.route();
        }).isInstanceOf(Exception.class);
    }

    @Test
    void printEx() {
        Controller controller = new Controller();
        try {
            controller.route();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }
}
