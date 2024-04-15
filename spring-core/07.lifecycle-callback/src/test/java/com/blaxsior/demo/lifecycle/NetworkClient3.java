package com.blaxsior.demo.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient3 {
    private String url;

    public NetworkClient3() {
        System.out.println("url = " + url);
        connect();
        call("초기화 메시지");
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message: " + message );
    }

    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 메시지");
    }

    @PreDestroy
    public void dispose() throws Exception {
        call("종료 메시지");
    }
}
