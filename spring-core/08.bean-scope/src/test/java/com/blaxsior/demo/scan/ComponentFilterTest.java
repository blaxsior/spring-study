package com.blaxsior.demo.scan;

import com.blaxsior.demo.scan.filter.BeanA;
import com.blaxsior.demo.scan.filter.BeanB;
import com.blaxsior.demo.scan.filter.MyExcludeComponent;
import com.blaxsior.demo.scan.filter.MyIncludeComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
public class ComponentFilterTest {
    @Test
    void filterScan() {
        var ctx = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ctx.getBean("beanA", BeanA.class);

        assertThat(beanA).isInstanceOf(BeanA.class);

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ctx.getBean("beanB", BeanB.class);
        });
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig { }
}
