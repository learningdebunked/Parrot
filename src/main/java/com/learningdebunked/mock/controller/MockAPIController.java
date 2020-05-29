package com.learningdebunked.mock.controller;

import com.learningdebunked.mock.api.MockAPI;
import com.learningdebunked.mock.processor.FileProcessor;
import com.learningdebunked.mock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${dir.url}")
    private String dirPath;

    @Autowired
    private FileProcessor fileProcessor;

    public String getGetResponse() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String srchKey = request.getAttribute("lookupResourceKey").toString();
        String templateFileName=  searchService.findTemplate(srchKey).getFile();
        return fileProcessor.extractTemplate(dirPath+"/"+templateFileName);
    }

    @Override
    public String getPostResponse() {

        //TODO
        return null;
    }
}
