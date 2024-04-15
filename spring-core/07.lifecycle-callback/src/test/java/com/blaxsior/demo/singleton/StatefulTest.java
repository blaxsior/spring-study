package com.blaxsior.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulTest {
    @Test
    void statefulSingleton() {
        var ctx = new AnnotationConfigApplicationContext(TestConfig.class);

        var statefulService1 = ctx.getBean("statefulService", StatefulService.class);
        var statefulService2 = ctx.getBean("statefulService", StatefulService.class);

        // A, B 스레드가 동시에 실행되는 중

        // A 사용자 요청
        statefulService1.order("A", 100);

        // B 사용자 요청
        statefulService2.order("B", 500);

        // A 사용자 조회
        var price = statefulService1.getPrice();
        System.out.println(price); // 100을 기대하겠지만, B가 변경한 500이 되어버림

        // 자신과 관계 없는 요청이 영향을 준다.
        Assertions.assertThat(statefulService1).isSameAs(statefulService2);
        Assertions.assertThat(price).isEqualTo(500);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
