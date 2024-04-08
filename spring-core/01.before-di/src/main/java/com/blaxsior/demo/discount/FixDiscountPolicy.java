package com.blaxsior.demo.discount;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;

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
