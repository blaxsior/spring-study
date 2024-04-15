package com.blaxsior.demo.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {
    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
    }
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singleton init");
        }
        @PreDestroy
        public void dispose() {
            System.out.println("singleton dispose");
        }
    }
}
