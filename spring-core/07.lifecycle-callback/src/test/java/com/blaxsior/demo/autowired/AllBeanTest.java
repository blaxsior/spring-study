package com.blaxsior.demo.autowired;

import com.blaxsior.demo.AutoAppConfig;
import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        var ctx = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ctx.getBean(DiscountService.class);
        Member member = new Member(1L, "test user", Grade.VIP);

        int discountValue = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountValue).isEqualTo(1000);

        int discountValue2 = discountService.discount(member, 23000, "rateDiscountPolicy");
        assertThat(discountValue2).isEqualTo(2300);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int value, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            if(discountPolicy != null) {
                return discountPolicy.discount(member, value);
            }
            return 0;
        }
    }
}
