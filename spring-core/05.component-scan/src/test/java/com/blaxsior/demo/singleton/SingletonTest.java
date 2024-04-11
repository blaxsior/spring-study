package com.blaxsior.demo.singleton;

import com.blaxsior.demo.core.AppConfig;
import com.blaxsior.demo.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("기본 컨테이너 테스트")
    void beforeSingleton() {
        AppConfig config = new AppConfig();
        
        var repo1 = config.memberRepository();
        var repo2 = config.memberRepository();
        
        // 참조하는 두 객체는 다름. => 요청할 때마다 새로 생성하니까
        System.out.println(repo1);
        System.out.println(repo2);

        Assertions.assertThat(repo1).isNotSameAs(repo2);
    }

    @Test
    @DisplayName("싱글톤 클래스 테스트")
    void testSingletonTest() {
        var singletonService1 = SingletonService.getInstance();
        var singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너 테스트")
    void afterSpringContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        var repo1 = ac.getBean("memberService", Member.class);
        var repo2 = ac.getBean("memberService", Member.class);

        // 참조하는 두 객체 동일. => 내부적으로 싱글톤으로 관리해줌
        System.out.println(repo1);
        System.out.println(repo2);

        Assertions.assertThat(repo1).isSameAs(repo2);
    }
}
