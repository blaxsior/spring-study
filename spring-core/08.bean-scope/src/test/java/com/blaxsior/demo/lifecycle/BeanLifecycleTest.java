package com.blaxsior.demo.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {
    @Test
    public void lifecycleTest() {
        // close 같은 인터페이스를 가지고 있는 상위 인터페이스
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(LifecycleConfig.class);
        NetworkClient1 client = ctx.getBean(NetworkClient1.class);
        ctx.close();
    }

    @Configuration
    static class LifecycleConfig {
        @Bean
        public NetworkClient1 networkClient1() {
            NetworkClient1 networkClient = new NetworkClient1();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean(initMethod = "init", destroyMethod = "dispose")
        public NetworkClient2 networkClient2() {
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean
        public NetworkClient3 networkClient3() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
