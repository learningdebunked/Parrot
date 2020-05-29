package com.learningdebunked.mock.listener;

import com.learningdebunked.mock.service.FileWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * This listener class is used to invoke the file watcher service post the application context load
 *
 * @author Kapil
 * @created 22/05/2020 - 4:17 PM
 * @project mock
 */
@Component
public class MockApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FileWatcherService fileWatcherService;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        fileWatcherService.monitor();
    }
}
