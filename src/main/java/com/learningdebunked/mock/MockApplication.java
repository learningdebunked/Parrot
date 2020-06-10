package com.learningdebunked.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MockApplication {

    //TODO integrate swagger aggregator using inspector
    //compare mock service and wire mock
    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }

}
