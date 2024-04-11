package com.blaxsior.demo.scan;

import com.blaxsior.demo.AutoAppConfig;
import com.blaxsior.demo.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {
    @Test
    @DisplayName("ComponentScan / Autowired 기반 config 테스트")
    void basicScan() {
        var ctx = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ctx.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
