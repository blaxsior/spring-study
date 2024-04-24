package com.blaxsior.db.exception.basic;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {
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

    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("sql exception");
        }
    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("sql exception");
        }
    }
}
