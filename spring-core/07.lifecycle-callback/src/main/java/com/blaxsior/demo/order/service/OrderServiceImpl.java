package com.blaxsior.demo.order.service;

import com.blaxsior.demo.annotation.MainDiscountPolicy;
import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;


    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {

        System.out.println("memberRepository = " + memberRepository + ", rateDiscountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        System.out.println("memberRepository = " + memberRepository + ", rateDiscountPolicy = " + rateDiscountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired(required = false) // @Autowired + 필드 / 파라미터 이름으로 매칭
    public void setDiscountPolicy(DiscountPolicy rateDiscountPolicy) {
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        this.discountPolicy = rateDiscountPolicy;
    }
    public MemberRepository getMemberRepository() {
        return memberRepository;
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
