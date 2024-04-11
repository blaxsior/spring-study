package com.blaxsior.demo.order.service;

import com.blaxsior.demo.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
