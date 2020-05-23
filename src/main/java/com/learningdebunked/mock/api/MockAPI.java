package com.learningdebunked.mock.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * API Interface to create and setup the app end points
 */
public interface MockAPI {

    @GetMapping("/mock/v1/*")
    String getResponse();

    @GetMapping("/mock/v1/")
    String getResponse1();
}
