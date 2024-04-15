package com.blaxsior.demo.order.service;

import com.blaxsior.demo.annotation.MainDiscountPolicy;
import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.order.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OrderServiceImpl2 implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl2(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
