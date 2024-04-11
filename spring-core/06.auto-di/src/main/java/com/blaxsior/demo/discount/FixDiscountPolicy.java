package com.blaxsior.demo.discount;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountAmmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountAmmount;
        }

        return 0;
    }
}
