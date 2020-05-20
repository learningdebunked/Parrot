package com.learningdebunked.mock.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Kapil
 * @project Mock Service
 */
@Component
public class FileWatcherService {

    @Value("${dir.url}")
    String dirUrl;

    //TODO pick the directory holding templates to be monitored from application.properties or as default argument
    public  void monitor(){
        System.out.println("monitor in place");
        System.out.println("dirUrl" + dirUrl);
    }
}
