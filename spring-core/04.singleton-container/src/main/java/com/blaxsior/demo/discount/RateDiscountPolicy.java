package com.blaxsior.demo.discount;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;

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
