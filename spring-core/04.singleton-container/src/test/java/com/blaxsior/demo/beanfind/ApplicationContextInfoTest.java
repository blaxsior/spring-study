package com.blaxsior.demo.beanfind;

import com.blaxsior.demo.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 스프링 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(var beanName: beanDefinitionNames) {
            Object bean = ac.getBean(beanName);
            System.out.println("name: " +beanName + " , object: " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(var beanName: beanDefinitionNames) {
            BeanDefinition definition = ac.getBeanDefinition(beanName);

            if(definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanName);
                System.out.println("name: " +beanName + " , object: " + bean);
            }
        }
    }
}
