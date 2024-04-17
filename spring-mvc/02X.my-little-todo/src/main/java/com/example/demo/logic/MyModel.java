package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyModel {
    private Map<String, Object> store = new HashMap<>();

    public void addAttribute(String key, Object value) {
        store.put(key, value);
    }

    public Object getAttribute(String key) {
        return store.get(key);
    }

    public List<String> getAttributeNames() {
        return new ArrayList<>(store.keySet());
    }
}
