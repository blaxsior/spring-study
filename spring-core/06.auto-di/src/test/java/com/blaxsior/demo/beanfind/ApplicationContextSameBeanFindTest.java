package com.blaxsior.demo.beanfind;

import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBeanConfig.class);

    @Test
    @DisplayName("타입 조회 시 동일 타입 bean 여러개면 예외 발생")
    void findBeanByTypeDupWillThrowException() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
//            ac.getBean(MemberRepository.class);
            ac.getBean(MemberRepository.class);
        });
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findBeanByTypeAll() {
        var beans = ac.getBeansOfType(MemberRepository.class);

        for(var key: beans.keySet()) {
            System.out.println("key = " + key + " value = " + beans.get(key));
        }
        System.out.println("beansOfType: " + beans);

        assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    static class TestBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
