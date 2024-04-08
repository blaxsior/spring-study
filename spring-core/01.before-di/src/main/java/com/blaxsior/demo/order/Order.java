package com.blaxsior.demo.order;

public class Order {
    private Long memberId;
    private String name;
    private int price;
    private int discountPrice;

    public Order(Long memberId, String name, int price, int discountPrice) {
        this.memberId = memberId;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return this.price - this.discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
