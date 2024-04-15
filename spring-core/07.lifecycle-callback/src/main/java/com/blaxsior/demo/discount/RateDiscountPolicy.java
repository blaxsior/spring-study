package com.blaxsior.demo.discount;

import com.blaxsior.demo.annotation.MainDiscountPolicy;
import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("rateDiscountPolicy")
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPersent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPersent / 100;
        }
        return 0;
    }

}
