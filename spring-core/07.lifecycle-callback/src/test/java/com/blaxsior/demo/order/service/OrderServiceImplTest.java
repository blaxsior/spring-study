package com.blaxsior.demo.order.service;

import com.blaxsior.demo.discount.FixDiscountPolicy;
import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.repository.MemberRepository;
import com.blaxsior.demo.member.repository.MemoryMemberRepository;
import com.blaxsior.demo.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "john", Grade.VIP));

        FixDiscountPolicy policy = new FixDiscountPolicy();

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, policy);

        Order order = orderService.createOrder(1L, "item1", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}