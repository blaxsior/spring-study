package com.blaxsior.demo.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("order: " + name + " , " + price);

        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
