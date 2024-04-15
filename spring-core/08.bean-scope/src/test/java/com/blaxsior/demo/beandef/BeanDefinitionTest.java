package com.blaxsior.demo.beandef;

import com.blaxsior.demo.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    //    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");


    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefNames = ac.getBeanDefinitionNames();
        for (var name : beanDefNames) {
            BeanDefinition def = ac.getBeanDefinition(name);

            if (def.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("name: " + name + ", def: " + def);
            }
        }
    }
}
