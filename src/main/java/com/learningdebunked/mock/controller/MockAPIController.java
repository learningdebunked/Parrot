package com.learningdebunked.mock.controller;

import com.learningdebunked.mock.api.MockAPI;
import com.learningdebunked.mock.filter.MRequest;
import com.learningdebunked.mock.filter.MThreadLocalObjectStorage;
import com.learningdebunked.mock.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Kapil
 * @project Mock Service
 */
@RestController
public class MockAPIController implements MockAPI {

    @Autowired
    private SearchService searchService;


    public String getGetResponse() {
        MRequest mrequest = MThreadLocalObjectStorage.getThreadLocal().get();
        return searchService.extractTemplate(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
    }

    @Override
    public String getPostResponse() {
        return searchService.extractTemplate(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
    }

    @Override
    public String getPutResponse() {
        return searchService.extractTemplate(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
    }

    @Override
    public String getDelResponse() {
        return searchService.extractTemplate(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
    }
}
