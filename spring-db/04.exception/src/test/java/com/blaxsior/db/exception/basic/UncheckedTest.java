package com.blaxsior.db.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UncheckedTest {

    /**
     * RuntimeException 상속 => 컴파일러가 검사 강제 X
     */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    /**
     * 예외 던질 때 throws 강제 X
     */
    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                System.out.println("e = " + e.getMessage());
            }
        }


        public void callThrow() {
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("unchecked exception");
        }
    }

    @Test
    void callCatch() {
        var service = new Service();
        service.callCatch();
    }

    @Test
    void callThrow() {
        var service = new Service();

        Assertions.assertThatThrownBy(() -> {
            service.callThrow();
        }).isInstanceOf(MyUncheckedException.class);

    }
}
