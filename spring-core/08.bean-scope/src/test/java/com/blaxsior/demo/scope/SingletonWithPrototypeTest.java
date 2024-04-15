package com.blaxsior.demo.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ctx.getBean(PrototypeBean.class);
        bean1.increment();
        Assertions.assertEquals(bean1.getCount(), 1);

        PrototypeBean bean2 = ctx.getBean(PrototypeBean.class);
        bean2.increment();
        Assertions.assertEquals(bean2.getCount(), 1);
    }

    @Test
    void singletonProto() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ctx.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertEquals(count1, 1);

        ClientBean clientBean2 = ctx.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertEquals(count2, 2);
    }

    @Scope("singleton")
    static class ClientBean
    {
        private Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
//            this.prototypeBean.increment();
//            int count = prototypeBean.getCount();
//            return count;
            PrototypeBean prototypeBean = this.prototypeBeanProvider.get();
            prototypeBean.increment();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void increment() {
            this.count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototype init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype destroy");
        }
    }
}
