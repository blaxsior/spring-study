package com.study.blaxsior.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HelloData {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "HelloData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
