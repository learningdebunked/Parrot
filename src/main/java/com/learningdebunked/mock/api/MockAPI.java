package com.learningdebunked.mock.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * API Interface to create and setup the app end points
 */
public interface MockAPI {

    @GetMapping("/mock/v1/")
    String getGetResponse();

    @PostMapping("/mock/v1")
    String getPostResponse();

    @PutMapping("/mock/v1/")
    String getPutResponse();

    @DeleteMapping("/mock/v1")
    String getDelResponse();

}
