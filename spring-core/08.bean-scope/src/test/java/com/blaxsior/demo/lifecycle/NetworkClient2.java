package com.blaxsior.demo.lifecycle;

public class NetworkClient2 {
    private String url;

    public NetworkClient2() {
        System.out.println("url = " + url);
        init();
        call("초기화 메시지");
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void init() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message: " + message );
    }

    public void dispose() {
        System.out.println("disconnect: " + url);
    }
}
