package com.learningdebunked.mock.controller;

import com.learningdebunked.mock.api.MockAPI;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kapil
 * @project Mock Service
 */
@RestController
public class MockAPIController implements MockAPI {

    @Override
    public String getResponse() {
        return "Kapil";
    }
}
