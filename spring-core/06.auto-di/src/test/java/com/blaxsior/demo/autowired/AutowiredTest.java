package com.blaxsior.demo.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredOption() {
        var ctx = new AnnotationConfigApplicationContext(Testclass.class);

        var bean = ctx.getBean(Testclass.class);
    }

    @Component
    static class Testclass {
        @Autowired(required = false)
        public void setBean1(NoInjectBean bean1) {
            System.out.println("nobean1 " + bean1);
        }

        @Autowired
        public void setBean2(@Nullable NoInjectBean bean2) {
            System.out.println("nobean2 " + bean2);
        }

        @Autowired
        public void setBean3(Optional<NoInjectBean> bean3) {
            System.out.println("nobean3 " + bean3);
        }
    }

    static class NoInjectBean {

    }
}
