package com.blaxsior.demo.core;

import com.blaxsior.demo.discount.DiscountPolicy;
import com.blaxsior.demo.discount.FixDiscountPolicy;
import com.blaxsior.demo.discount.RateDiscountPolicy;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.member.repository.MemoryMemberRepository;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import com.blaxsior.demo.order.service.OrderService;
import com.blaxsior.demo.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 각 역할에 대한 구현이 한 눈에 들어나게 구성해야 한다.
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(MemberRepository memberRepository) {
        return new MemberServiceImpl(memberRepository);
    }
    @Bean
    public OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        return new OrderServiceImpl(
                memberRepository,
                discountPolicy
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
