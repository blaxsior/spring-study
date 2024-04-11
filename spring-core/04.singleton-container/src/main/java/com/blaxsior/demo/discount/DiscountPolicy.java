package com.blaxsior.demo.discount;

import com.blaxsior.demo.member.Member;

public interface DiscountPolicy {
    public int discount(Member member, int price);
}
