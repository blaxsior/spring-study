package com.blaxsior.demo.order.service;

import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.discount.FixDiscountPolicy;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import com.blaxsior.demo.order.Order;

public class OrderServiceImpl implements OrderService {
    private MemberService memberService = new MemberServiceImpl();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        var optMember = this.memberService.findMember(memberId);
        if(optMember.isEmpty()) {
            return null;
        }

        var member = optMember.get();
        var discountPrice = this.discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
