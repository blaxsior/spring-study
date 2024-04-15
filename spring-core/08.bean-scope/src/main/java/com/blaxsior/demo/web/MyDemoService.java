package com.blaxsior.demo.web;

import com.blaxsior.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyDemoService {
//    private final ObjectProvider<MyLogger> loggerProvider;
    private final MyLogger logger;

    public void logic(String id) {
//        var logger = this.loggerProvider.getObject();
        logger.log("service id = " + id);
    }
}
