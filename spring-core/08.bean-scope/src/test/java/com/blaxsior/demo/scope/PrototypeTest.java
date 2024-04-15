package com.blaxsior.demo.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
    }
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("proto init");
        }
        @PreDestroy
        public void dispose() {
            System.out.println("proto dispose");
        }
    }
}
