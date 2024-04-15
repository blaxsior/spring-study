package com.blaxsior.demo.beanfind;

import com.blaxsior.demo.core.AppConfig;
import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.discount.FixDiscountPolicy;
import com.blaxsior.demo.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시 자식 타입 bean 여러개면 예외 발생")
    void findBeansByParentTypeThrowException() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
//            ac.getBean(MemberRepository.class);
            ac.getBean(DiscountPolicy.class);
        });
    }

    @Test
    @DisplayName("타입 이름으로 빈 조회 가능")
    void findBeanByName() {
        var bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("하위 타입으로도 빈 조회 가능")
    void findBeanBySpecificType() {
        var bean = ac.getBean(RateDiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }



    @Test
    @DisplayName("부모 타입으로 연관 빈 모두 조회")
    void findBeansByParentType() {
        var beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        for(var key: beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType: " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Object로 빈 모두 조회 -> 내부 등록된 모든 빈 출력")
    void findBeansByObjectType() {
        var beansOfType = ac.getBeansOfType(Object.class);

        for(var key: beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType: " + beansOfType);

//        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy FixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
