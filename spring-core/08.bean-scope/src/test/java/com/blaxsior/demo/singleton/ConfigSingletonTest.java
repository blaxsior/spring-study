package com.blaxsior.demo.singleton;

import com.blaxsior.demo.core.AppConfig;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import com.blaxsior.demo.order.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigSingletonTest {
    @Test
    void configurationTest() {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ctx.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ctx.getBean("orderService", OrderServiceImpl.class);

        var repo1 = memberService.getMemberRepository();
        var repo2 = orderService.getMemberRepository();
        var repo3 = ctx.getBean("memberRepository", MemberRepository.class);

        System.out.println(repo1);
        System.out.println(repo2);
        System.out.println(repo3);

        Assertions.assertThat(repo1).isSameAs(repo2);
        Assertions.assertThat(repo1).isSameAs(repo3);
    }

    @Test
    void configurationTest2() {
        var pureAppConfig = new AppConfig();

        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        var springAppConfig = ctx.getBean(AppConfig.class);

        System.out.println("pure: " + pureAppConfig);
        System.out.println("spring: " + springAppConfig);
    }
}
