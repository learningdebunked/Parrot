package com.learningdebunked.mock;

import com.learningdebunked.mock.service.FileWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }

    @Component
    public static class FileWatcher {

        @Autowired
        FileWatcherService fileWatcherService;

        @PostConstruct
        public void initialize() throws IOException {
            fileWatcherService.monitor();
        }
    }
}
