package com.blaxsior.demo.discount;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_have_10p_discount() {
        var initPrice = 10000;
        var expectedAmmount = 1000;
        var member = new Member(1L, "vip", Grade.VIP);

        var discountAmmount = discountPolicy.discount(member, initPrice);

        Assertions.assertEquals(discountAmmount, expectedAmmount);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void no_vip_no_discount() {
        var initPrice = 10000;
        var member = new Member(1L, "normal", Grade.BASIC);

        var discountAmmount = discountPolicy.discount(member, initPrice);

        Assertions.assertEquals(discountAmmount, 0);
    }
}