package com.learningdebunked.mock.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * API Interface to create and setup the app end points
 */
public interface MockAPI {

    @GetMapping("/mock/v1/")
    String getGetResponse();

    @PostMapping("/mock/v1")
    String getPostResponse();
}
