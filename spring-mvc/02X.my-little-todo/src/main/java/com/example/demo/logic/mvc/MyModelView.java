package com.example.demo.logic.mvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyModelView {
    private String path;
    private MyModel model;

    public MyModelView(String path) {
        this.path = path;
        this.model = new MyModel();
    }

    public void addAttribute(String key, Object value) {
        this.model.addAttribute(key, value);
    }
}
