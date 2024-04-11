package com.blaxsior.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("test");
        System.out.println(helloLombok.getName());

        helloLombok.setAge(33);
        System.out.println(helloLombok.getAge());
    }
}
