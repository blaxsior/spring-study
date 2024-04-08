package com.blaxsior.demo.order.service;

import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.order.Order;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        var optMember = this.memberRepository.findById(memberId);
        if(optMember.isEmpty()) {
            return null;
        }

        var member = optMember.get();
        var discountPrice = this.discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
