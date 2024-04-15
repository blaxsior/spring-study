package com.blaxsior.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient1 implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient1() {
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

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 메시지");
    }

    @Override
    public void destroy() throws Exception {
        call("종료 메시지");
    }
}
