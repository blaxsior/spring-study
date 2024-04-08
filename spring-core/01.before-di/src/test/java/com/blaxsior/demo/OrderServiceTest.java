package com.blaxsior.demo;

import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import com.blaxsior.demo.order.service.OrderService;
import com.blaxsior.demo.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    private MemberService memberService = new MemberServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Long memberId = 1L;
        int expectedAmmount = 1000;
        var member = new Member(memberId, "test member", Grade.VIP);


        memberService.join(member);
        var order = orderService.createOrder(memberId, "item", 10000);

        Assertions.assertEquals(order.getDiscountPrice(),expectedAmmount);
    }
}
