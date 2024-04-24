package com.blaxsior.db.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckedTest {

    /**
     * Exception => checked exception
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        Repository repository = new Repository();

        /**
         * 예외 잡아 처리. try-catch 또는 throws로 명시해야 함
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                System.out.println("e = " + e.getMessage());
            }
        }

        /**
         * 예외를 받아 던짐. 예외를 명시해야 한다!
         * @throws MyCheckedException
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("my exception");
        }
    }

    @Test
    void callAndCatch() {
        var service = new Service();
        service.callCatch();
    }

    @Test
    void callAndThrow() {
        var service = new Service();
        Assertions.assertThatThrownBy(() -> {
            service.callThrow();
        }).isInstanceOf(MyCheckedException.class);
    }
}
