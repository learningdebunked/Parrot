package com.learningdebunked.mock.controller;

import com.learningdebunked.mock.api.MockAPI;
import com.learningdebunked.mock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kapil
 * @project Mock Service
 */
@RestController
public class MockAPIController implements MockAPI {

    @Autowired
    private SearchService searchService;

    public String getResponse() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String srchKey = request.getAttribute("lookupResourceKey").toString();
        return searchService.findTemplate(srchKey).getFile();
    }
}
